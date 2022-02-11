package com.task.backend.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.backend.payload.response.TicketDTO;
import com.task.backend.service.TicketService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() throws Exception {
        List<TicketDTO> ticketDTOs = ticketService.findAll();
        return ResponseEntity.ok()
                .body(ticketDTOs);
    }
    @GetMapping("/all_by_priority")
    public ResponseEntity<?> findTicketsByPriority() throws Exception {
        List<TicketDTO> ticketDTOs = ticketService.findAllByPriority();
        return ResponseEntity.ok()
                .body(ticketDTOs);
    }


}
