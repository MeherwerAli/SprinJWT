package com.task.backend.repository;

import com.task.backend.model.DeliveryDetails;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryDetailsRepository extends CrudRepository<DeliveryDetails, Integer> {
}
