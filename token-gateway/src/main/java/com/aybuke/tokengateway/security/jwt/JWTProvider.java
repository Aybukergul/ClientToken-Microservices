package com.aybuke.tokengateway.security.jwt;

import com.aybuke.tokengateway.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTProvider implements IJWTProvider{
    @Value("${authentication.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

    private final String JWT_TOKEN_PREFIX = "Bearer";
    private final String JWT_HEADER_STRING = "Authorization";

    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public JWTProvider(@Value("${authentication.jwt.public-key}") String publicKeyStr,
                       @Value("${authentication.jwt.private-key}") String privateKeyStr){
        try{
            KeyFactory keyFactory = getKeyFactory();
            Base64.Decoder decoder = Base64.getDecoder();
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decoder.decode(privateKeyStr.getBytes()));
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decoder.decode(publicKeyStr.getBytes()));

            privateKey = keyFactory.generatePrivate(privateKeySpec);
            publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch(Exception e){
            throw new RuntimeException("Invalid key spec", e);
        }
    }

    @Override
    public String generateToken(UserPrincipal userPrincipal){
        String authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return Jwts.builder().setSubject(userPrincipal.getUsername())
                .claim("userId", userPrincipal.getId())
                .claim("roles", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest httpServletRequest){
        String token = resolveToken(httpServletRequest);

        if(token == null){
            return null;
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails userDetails = new UserPrincipal(userId, username, null);
        return username != null ? new UsernamePasswordAuthenticationToken(userDetails, null, authorities) : null;
    }

    @Override
    public boolean validateToken(HttpServletRequest httpServletRequest){
        String token = resolveToken(httpServletRequest);

        if(token == null){
            return false;
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        if(claims.getExpiration().before(new Date()))
            return false;

        return true;
    }

    public String resolveToken(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader(JWT_HEADER_STRING);
        if(bearerToken != null && bearerToken.startsWith(JWT_TOKEN_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }

    private KeyFactory getKeyFactory(){
        try{
            return KeyFactory.getInstance("RSA");
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Unknown Key Generation", e);
        }
    }

}
