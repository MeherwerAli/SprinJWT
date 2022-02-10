package com.task.backend.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task.backend.model.User;
import com.task.backend.payload.request.LoginRequest;
import com.task.backend.payload.request.SignupRequest;
import com.task.backend.payload.response.JWTResponseToken;
import com.task.backend.repository.UserRepository;
import com.task.backend.security.jwt.AuthEntryPointJwt;
import com.task.backend.security.jwt.JwtUtils;
import com.task.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Boolean checkUserNameExist(String userName) {

        return userRepository.existsByUserName(userName);
    }

    @Override
    public Boolean checkEmailExist(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public JWTResponseToken signupUser(SignupRequest signUpRequest) throws Exception{
        try{
            if(signUpRequest.getPassword() == null)
                return new JWTResponseToken(null, null, "Password cannot be empty");
            else if(signUpRequest.getEmail() == null)
                return new JWTResponseToken(null, null, "Email cannot be empty");
            else if(signUpRequest.getUsername() == null)
                return new JWTResponseToken(null, null, "User Name cannot be empty");
            User user = new User(
                    signUpRequest.getFirstName(),
                    signUpRequest.getLastName(),
                    signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            user = userRepository.save(user);
            JWTResponseToken response = AuthenticateUser(new LoginRequest(user.getUserName(), signUpRequest.getPassword()));
            response.setMessage("Signup Successfull !!!");
            return response;
        }catch (Exception ex){
            logger.error("Error:"+ ex.getMessage());
            throw new Exception(ex);
        }
    }

    @Override
    public JWTResponseToken signinUser(LoginRequest loginRequest) throws Exception{
        try{
            JWTResponseToken response = AuthenticateUser(loginRequest);
            response.setMessage("Signin Successfull !!!");
            return response;
        }catch (Exception ex){
            logger.error("Error:"+ ex.getMessage());
            throw new Exception(ex);
        }
    }

    @Override
    public void removeUserByUserName(String userName) throws Exception{
        try{
            User user = userRepository.findByUserName(userName).get();
            userRepository.delete(user);
        }catch (Exception ex){
            logger.error("Error:"+ ex.getMessage());
            throw new Exception(ex);
        }
    }

    public JWTResponseToken AuthenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());

        return new JWTResponseToken(userDetails.getUsername(),jwtToken);
    }
}
