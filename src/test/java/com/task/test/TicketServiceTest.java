package com.task.test;

import com.task.backend.model.Priority;
import com.task.backend.payload.request.LoginRequest;
import com.task.backend.payload.request.SignupRequest;
import com.task.backend.payload.response.JWTResponseToken;
import com.task.backend.payload.response.TicketDTO;
import com.task.backend.service.TicketService;
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

import java.util.ArrayList;

@RunWith(SpringRunner.class )
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TicketServiceTest {

    @Autowired
    TicketService ticketService;

    @Test
    public void getAllTicketsByPriorityTest(){
        try {
            ArrayList<TicketDTO> ticketDTOs = new ArrayList<>(ticketService.findAllByPriority());
            Assertions.assertEquals(Priority.VERY_HIGH, Priority.valueOf(ticketDTOs.get(0).getPriority()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
