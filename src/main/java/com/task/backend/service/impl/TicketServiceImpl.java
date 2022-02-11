package com.task.backend.service.impl;

import com.task.backend.converter.TicketConverter;
import com.task.backend.model.Ticket;
import com.task.backend.payload.response.TicketDTO;
import com.task.backend.repository.TicketRepository;
import com.task.backend.service.TicketService;
import com.task.backend.util.AppConstants;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<TicketDTO> findAll() throws NotFoundException {
        try {
            return TicketConverter.ticketDTOList(
                    new ArrayList<>((Collection<? extends Ticket>) ticketRepository.findAll()));
        } catch (Exception ex) {
            logger.error(AppConstants.ERROR_STRING +"%s", ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
    }

    @Override
    public List<TicketDTO> findAllByPriority() throws NotFoundException {
        try {
            return TicketConverter.ticketDTOList(
                    new ArrayList<>(ticketRepository.findAllByOrderByPriorityAsc()));
        } catch (Exception ex) {
            logger.error(AppConstants.ERROR_STRING + ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
    }
}
