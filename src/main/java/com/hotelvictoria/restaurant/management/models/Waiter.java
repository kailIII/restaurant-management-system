package com.hotelvictoria.restaurant.management.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    public Waiter() {
    }

    public Waiter(String name) {
        this.name = name;
    }

    /**
     * @return Waiter unique ID
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return Name of the waiter
     */
    public String getName() {
        return name;
    }
}
