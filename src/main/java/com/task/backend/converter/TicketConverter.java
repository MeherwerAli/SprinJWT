package com.task.backend.converter;

import com.task.backend.model.Priority;
import com.task.backend.model.Ticket;
import com.task.backend.payload.response.TicketDTO;
import com.task.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class TicketConverter {

    @Autowired
    private static TicketRepository ticketRepository;

    public static Ticket toDAO(TicketDTO ticketDTO){
        Ticket ticketDAO = new Ticket();


        ticketDAO.setId(ticketDTO.getId());
        ticketDAO.setDeliveryDetails(ticketRepository.findById(ticketDTO.getDeliveryId()).get().getDeliveryDetails());
        ticketDAO.setName(ticketDTO.getName());
        ticketDAO.setPriority(Priority.valueOf(ticketDTO.getPriority()));
        return ticketDAO;
    }
    public static TicketDTO toDTO(Ticket ticketEntity){
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticketEntity.getId());
        Long deliveryDetailsId = ticketEntity.getDeliveryDetails().getId();
        ticketDTO.setDeliveryId(deliveryDetailsId.intValue());
        ticketDTO.setName(ticketEntity.getName());
        ticketDTO.setPriority(ticketEntity.getPriority());

        return ticketDTO;
    }

    public static ArrayList<Ticket> ticketDAOList(ArrayList<TicketDTO> ticketDTOs){
        ArrayList<Ticket> ticketDAOs = new ArrayList<Ticket>();
        ticketDTOs.forEach(
                dto -> ticketDAOs.add(toDAO(dto)
                )
        );
        return ticketDAOs;
    }

    public static ArrayList<TicketDTO> ticketDTOList(ArrayList<Ticket> ticketDAOs){
        ArrayList<TicketDTO> ticketDTOs = new ArrayList<TicketDTO>();
        ticketDAOs.forEach(
                dao -> ticketDTOs.add(toDTO(dao)
                )
        );
        return ticketDTOs;
    }
}
