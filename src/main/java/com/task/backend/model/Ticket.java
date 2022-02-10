package com.task.backend.model;

import javax.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    private DeliveryDetails deliveryDetails;

    @Column(name="priority")
    private int priority;

    public Ticket() {
    }

    public Ticket(String name, DeliveryDetails deliveryDetails, Priority priority) {
        this.name = name;
        this.deliveryDetails = deliveryDetails;
        this.priority = priority.value;
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

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public Priority getPriority() {
        return Priority.valueOfLabel(priority);
    }

    public void setPriority(Priority priority) {
        this.priority = priority.value;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryId=" + deliveryDetails.getId() +
                ", priority=" + priority +
                '}';
    }
}
