package com.task.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="delivery_details")
public class DeliveryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="delivery_id")
    private long id;

    @Column(name="customer_type")
    private CustomerType customerType;

    @Column(name="delivery_status")
    private DeliveryStatus deliveryStatus;

    @Column(name="expected_delivery_time")
    private Date expectedDeliveryTime;

    @Column(name="current_distance_from_destination_in_meters")
    private Integer currentDistance;

    @Column(name="time_to_reach_destination")
    private Date timeToReachDestination;

    @OneToOne(mappedBy = "deliveryDetails")
    private Ticket ticket;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(Date expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public Integer getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(Integer currentDistance) {
        this.currentDistance = currentDistance;
    }

    public Date getTimeToReachDestination() {
        return timeToReachDestination;
    }

    public void setTimeToReachDestination(Date timeToReachDestination) {
        this.timeToReachDestination = timeToReachDestination;
    }

    protected DeliveryDetails() {
    }

    public DeliveryDetails(CustomerType customerType, DeliveryStatus deliveryStatus, Date expectedDeliveryTime, Integer currentDistance, Date timeToReachDestination) {
        this.customerType = customerType;
        this.deliveryStatus = deliveryStatus;
        this.expectedDeliveryTime = expectedDeliveryTime;
        this.currentDistance = currentDistance;
        this.timeToReachDestination = timeToReachDestination;
    }

    @Override
    public String toString() {
        return "DeliveryDetails{" +
                "id=" + id +
                ", customerType='" + customerType + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", expectedDeliveryTime=" + expectedDeliveryTime +
                ", currentDistance=" + currentDistance +
                ", timeToReachDestination=" + timeToReachDestination +
                '}';
    }
}
