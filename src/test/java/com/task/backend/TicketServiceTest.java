package com.task.backend;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.backend.model.enums.Priority;
import com.task.backend.payload.response.TicketDTO;
import com.task.backend.service.TicketService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {

    @Autowired
    TicketService ticketService;

    @Test
    public void getAllTicketsByPriorityTest() {
        try {
            ArrayList<TicketDTO> ticketDTOs = new ArrayList<>(ticketService.findAllByPriority());
            Assertions.assertEquals(Priority.VERY_HIGH, Priority.valueOf(ticketDTOs.get(0).getPriority()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
