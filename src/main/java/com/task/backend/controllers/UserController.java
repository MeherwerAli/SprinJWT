package com.task.backend.controllers;


import com.task.backend.model.User;
import com.task.backend.payload.request.LoginRequest;
import com.task.backend.payload.request.SignupRequest;
import com.task.backend.payload.response.JWTResponseToken;
import com.task.backend.payload.response.MessageResponse;
import com.task.backend.payload.response.UserInfoResponse;
import com.task.backend.repository.UserRepository;
import com.task.backend.security.jwt.JwtUtils;
import com.task.backend.service.UserService;
import com.task.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        ResponseEntity<?> response = ResponseEntity.ok()
                .body(userService.signinUser(loginRequest));
        return response;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {

        if (userService.checkUserNameExist(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.checkEmailExist(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        return ResponseEntity.ok(userService.signupUser(signUpRequest));
    }


    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody String userName) throws Exception {
        userService.removeUserByUserName(userName);
        return ResponseEntity.ok("user deleted!!!");
    }

}
