package com.task.backend.service.impl;

import com.task.backend.converter.TicketConverter;
import com.task.backend.model.Ticket;
import com.task.backend.payload.response.TicketDTO;
import com.task.backend.repository.TicketRepository;
import com.task.backend.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public List<TicketDTO> findAll() throws Exception{
        try{
            List<TicketDTO> ticketDTOs = TicketConverter.ticketDTOList(
                    new ArrayList<>((Collection<? extends Ticket>) ticketRepository.findAll()));
            return ticketDTOs;
        }catch (Exception ex){
            logger.error("Error:"+ ex.getMessage());
            throw new Exception(ex);
        }
    }

    @Override
    public List<TicketDTO> findAllByPriority() throws Exception{
        try{
            List<TicketDTO> ticketDTOs = TicketConverter.ticketDTOList(
                    new ArrayList<Ticket>((Collection<? extends Ticket>) ticketRepository.findAllByOrderByPriorityAsc()));
            return ticketDTOs;

        }catch (Exception ex){
            logger.error("Error:"+ ex.getMessage());
            throw new Exception(ex);
        }
    }
}
