package com.task.backend.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.backend.payload.request.LoginRequest;
import com.task.backend.payload.request.SignupRequest;
import com.task.backend.payload.response.MessageResponse;
import com.task.backend.service.UserService;

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
