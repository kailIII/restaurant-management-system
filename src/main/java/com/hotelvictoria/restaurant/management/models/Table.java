package com.hotelvictoria.restaurant.management.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    public Table() {
    }

    public Table(String name) {
        this.name = name;
    }

    /**
     * @return Table unique ID
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return Name of the table
     */
    public String getName() {
        return name;
    }
}
