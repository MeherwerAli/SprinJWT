package com.task.backend.controllers;


import com.task.backend.payload.request.LoginRequest;
import com.task.backend.payload.request.SignupRequest;
import com.task.backend.payload.response.JWTResponseToken;
import com.task.backend.service.UserService;
import com.task.backend.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/auth/signin")
    public ResponseEntity<JWTResponseToken> signInUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok()
                .body(userService.signinUser(loginRequest));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<JWTResponseToken> signUpUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userService.checkUserNameExist(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new JWTResponseToken(null, null, AppConstants.USERNAME_ALREADY_TAKEN));
        }

        if (userService.checkEmailExist(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new JWTResponseToken(null, null, AppConstants.EMAIL_ALREADY_IN_USE));
        }

        // Create new user's account
        return ResponseEntity.ok(userService.signupUser(signUpRequest));
    }


    @PutMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody String userName) throws Exception {
        userService.removeUserByUserName(userName);
        return ResponseEntity.ok("user deleted!!!");
    }

}
