package com.task.backend;

import com.task.backend.model.DeliveryDetails;
import com.task.backend.model.Ticket;
import com.task.backend.model.enums.CustomerType;
import com.task.backend.model.enums.DeliveryStatus;
import com.task.backend.model.enums.Priority;
import com.task.backend.repository.DeliveryDetailsRepository;
import com.task.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Transactional
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    DeliveryDetailsRepository deliveryDetailsRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if (ticketRepository.count() == 0) {
            Calendar date = Calendar.getInstance();
            long timeInSecs = date.getTimeInMillis();
            Date afterAdding30Mins = new Date(timeInSecs + (30 * 60 * 1000));
            Date afterAdding35Mins = new Date(timeInSecs + (35 * 60 * 1000));

            DeliveryDetails deliveryDetails = new DeliveryDetails(CustomerType.LOYAL, DeliveryStatus.ORDER_PREPAIRING, afterAdding30Mins, 30, afterAdding35Mins);
            deliveryDetails = deliveryDetailsRepository.save(deliveryDetails);

            Ticket ticket = new Ticket("testTicket", deliveryDetails, Priority.HIGH);
            ticketRepository.save(ticket);

            DeliveryDetails deliveryDetails1 = new DeliveryDetails(CustomerType.VIP, DeliveryStatus.ORDER_PREPAIRING, afterAdding30Mins, 30, afterAdding35Mins);
            deliveryDetails1 = deliveryDetailsRepository.save(deliveryDetails1);

            Ticket ticket1 = new Ticket("testTicket2", deliveryDetails1, Priority.VERY_HIGH);
            ticketRepository.save(ticket1);

            DeliveryDetails deliveryDetails2 = new DeliveryDetails(CustomerType.NEW, DeliveryStatus.ORDER_PICKEDUP, afterAdding30Mins, 30, afterAdding35Mins);
            deliveryDetails2 = deliveryDetailsRepository.save(deliveryDetails2);

            Ticket ticket2 = new Ticket("testTicket3", deliveryDetails2, Priority.MODERATE);
            ticketRepository.save(ticket2);

        }
    }
}
