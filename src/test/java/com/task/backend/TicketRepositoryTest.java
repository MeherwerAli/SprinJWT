package com.task.backend;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.backend.repository.TicketRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketRepositoryTest {
	
    @Autowired
    private TicketRepository ticketRepository;
    
    
    @Test
    public void findAllByPriority() {
    	
    	Assertions.assertTrue(ticketRepository.findAllByOrderByPriorityAsc().size()>0);
    	
    }
    
    

}
