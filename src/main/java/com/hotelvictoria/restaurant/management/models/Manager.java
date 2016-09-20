package com.hotelvictoria.restaurant.management.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    public Manager() {
    }

    public Manager(String name) {
        this.name = name;
    }

    /**
     * @return Manager unique ID
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return Name of the manager
     */
    public String getName() {
        return name;
    }
}
