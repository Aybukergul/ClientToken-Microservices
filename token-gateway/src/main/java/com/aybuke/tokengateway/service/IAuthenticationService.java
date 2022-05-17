package com.aybuke.tokengateway.service;

import com.aybuke.tokengateway.model.User;

public interface IAuthenticationService {
    String signInAndReturnJWT(User signInRequest);
}
