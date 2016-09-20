package com.hotelvictoria.restaurant.management.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    /**
     * @return Restaurant unique ID
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return Name of the restaurant
     */
    public String getName() {
        return name;
    }
}
