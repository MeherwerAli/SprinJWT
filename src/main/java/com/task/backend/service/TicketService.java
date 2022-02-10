package com.task.backend.service;

import com.task.backend.payload.response.TicketDTO;

import java.util.List;

public interface TicketService {
    List<TicketDTO> findAll() throws Exception;
    List<TicketDTO> findAllByPriority() throws Exception;
}
