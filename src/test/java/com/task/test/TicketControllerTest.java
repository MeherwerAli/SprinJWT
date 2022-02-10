package com.task.test;

import com.task.backend.controllers.TicketController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class )
@SpringBootTest
public class TicketControllerTest {


    @Test
    public void getAllTicketsByPriorityControllerTest(){
        TicketController ticketController = new TicketController();
        try {
            ResponseEntity<?> response = ticketController.findTicketsByPriority();
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
