package com.task.backend.payload.response;


import com.task.backend.model.Priority;

public class TicketDTO {
    private long id;

    private String name;

    private Integer deliveryId;

    private String priority;


    public TicketDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority.name();
    }
}
