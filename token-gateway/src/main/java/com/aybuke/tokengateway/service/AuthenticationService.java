package com.aybuke.tokengateway.service;

import com.aybuke.tokengateway.model.User;
import com.aybuke.tokengateway.security.UserPrincipal;
import com.aybuke.tokengateway.security.jwt.IJWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJWTProvider jwtProvider;

    @Override
    public String signInAndReturnJWT(User signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return jwtProvider.generateToken(userPrincipal);
    }
}
