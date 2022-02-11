package com.task.backend.service;

import com.task.backend.payload.request.LoginRequest;
import com.task.backend.payload.request.SignupRequest;
import com.task.backend.payload.response.JWTResponseToken;

public interface UserService {

    Boolean checkUserNameExist(String userName);

    Boolean checkEmailExist(String email);

    JWTResponseToken signupUser(SignupRequest signUpRequest) throws Exception;

    JWTResponseToken signinUser(LoginRequest loginRequest) throws Exception;

    void removeUserByUserName(String userName) throws Exception;
}
