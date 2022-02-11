package com.task.backend.service;

import com.task.backend.payload.response.TicketDTO;
import javassist.NotFoundException;

import java.util.List;

public interface TicketService {
    List<TicketDTO> findAll() throws NotFoundException;

    List<TicketDTO> findAllByPriority() throws NotFoundException;
}
