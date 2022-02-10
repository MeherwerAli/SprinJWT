package com.task.backend.repository;

import com.task.backend.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findAllByOrderByPriorityAsc();
}
