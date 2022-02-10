package com.task.test;

import com.task.backend.payload.request.LoginRequest;
import com.task.backend.payload.request.SignupRequest;
import com.task.backend.payload.response.JWTResponseToken;
import com.task.backend.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class )
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Before
    public void UserSignup() throws Exception {
        SignupRequest signupRequest = new SignupRequest(
                "TDtest",
                "User",
                "TDtestUser",
                "testUser@gmail.com",
                "abcd123456");
        JWTResponseToken response = userService.signupUser(signupRequest);
        Assertions.assertFalse(response.getJwtToken().isEmpty());

    }
    @Test
    public void UserSigninTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest("TDtestUser", "abcd123456");
        JWTResponseToken response = userService.signinUser(loginRequest);
        Assertions.assertFalse(response.getJwtToken().isEmpty());
    }

    @Test
    public void existingEmailTest(){
        Assertions.assertTrue(userService.checkEmailExist("testUser@gmail.com"));
    }
    @Test
    public void existingUserNameTest(){
        Assertions.assertTrue(userService.checkUserNameExist("TDtestUser"));
    }
    @Test
    public void wrongPasswordTest(){
        LoginRequest loginRequest = new LoginRequest("TDtestUser", "12345678");
        try {
            JWTResponseToken response = userService.signinUser(loginRequest);
        } catch (Exception e) {
            String msg = e.getMessage();
            Assertions.assertTrue(msg.matches("(.*)BadCredentials(.*)"));
        }
    }

    @After
    public void RemoveUsers() throws Exception {
        userService.removeUserByUserName("TDtestUser");
    }
}
