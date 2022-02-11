package com.task.backend.converter;

import com.task.backend.model.DeliveryDetails;
import com.task.backend.model.enums.Priority;
import com.task.backend.model.Ticket;
import com.task.backend.payload.response.TicketDTO;
import com.task.backend.repository.DeliveryDetailsRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TicketConverter {

    @Autowired
    private static DeliveryDetailsRepository deliveryDetailsRepository;
    private TicketConverter() {
        throw new IllegalStateException("TicketConverter class");
    }

    public static Ticket toDAO(TicketDTO ticketDTO) throws NotFoundException {
        Ticket ticketDAO = new Ticket();


        ticketDAO.setId(ticketDTO.getId());
        DeliveryDetails deliveryDetails = null;
        if(deliveryDetailsRepository.findById(ticketDTO.getDeliveryId()).isPresent()){
            deliveryDetails = deliveryDetailsRepository.findById(ticketDTO.getDeliveryId()).get();
            ticketDAO.setDeliveryDetails(deliveryDetails);
        }
        ticketDAO.setName(ticketDTO.getName());
        ticketDAO.setPriority(Priority.valueOf(ticketDTO.getPriority()));
        return ticketDAO;
    }

    public static TicketDTO toDTO(Ticket ticketEntity) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticketEntity.getId());
        Long deliveryDetailsId = ticketEntity.getDeliveryDetails().getId();
        ticketDTO.setDeliveryId(deliveryDetailsId.intValue());
        ticketDTO.setName(ticketEntity.getName());
        ticketDTO.setPriority(ticketEntity.getPriority());

        return ticketDTO;
    }

    public static List<Ticket> ticketDAOList(List<TicketDTO> ticketDTOs) {
        List<Ticket> ticketDAOs = new ArrayList<>();
        ticketDTOs.forEach(
                dto -> {
                    try {
                        ticketDAOs.add(toDAO(dto)
                        );
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );
        return ticketDAOs;
    }

    public static List<TicketDTO> ticketDTOList(List<Ticket> ticketDAOs) {
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        ticketDAOs.forEach(
                dao -> ticketDTOs.add(toDTO(dao)
                )
        );
        return ticketDTOs;
    }
}
