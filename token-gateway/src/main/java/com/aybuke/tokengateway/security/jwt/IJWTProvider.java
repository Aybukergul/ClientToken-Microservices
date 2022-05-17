package com.aybuke.tokengateway.security.jwt;

import com.aybuke.tokengateway.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IJWTProvider {
    String generateToken(UserPrincipal userPrincipal);

    Authentication getAuthentication(HttpServletRequest httpServletRequest);

    boolean validateToken(HttpServletRequest httpServletRequest);
}
