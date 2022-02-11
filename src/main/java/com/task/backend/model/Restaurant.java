package com.task.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "preparation_time")
    private String preparationTime;

    @Column(name = "mobile_number")
    private Integer mobileNumber;

    protected Restaurant() {
    }

    public Restaurant(String name, Integer rating, String preparationTime, Integer mobileNumber) {
        this.name = name;
        this.rating = rating;
        this.preparationTime = preparationTime;
        this.mobileNumber = mobileNumber;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", preparationTime='" + preparationTime + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
