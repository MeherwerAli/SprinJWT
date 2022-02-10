package com.task.test;

import com.task.backend.controllers.UserController;
import com.task.backend.payload.request.LoginRequest;
import com.task.backend.payload.request.SignupRequest;
import com.task.backend.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class )
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserService userService;

    @Before
    public void UserSignup() throws Exception {
        SignupRequest signupRequest = new SignupRequest(
                "Controller",
                "TestUser",
                "ControllerTestUser",
                "testUser@gmail.com",
                "abcd123456");
        UserController userController = new UserController();

        ResponseEntity<?> response = userController.signUpUser(signupRequest);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void UserSigninTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest("ControllerTestUser", "abcd123456");
        UserController userController = new UserController();
        ResponseEntity<?> response = userController.signInUser(loginRequest);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @After
    public void RemoveUsers() throws Exception {
        UserController userController = new UserController();
        ResponseEntity<?> response = userController.deleteUser("ControllerTestUser");
    }

}
