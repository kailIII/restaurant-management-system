package com.hotelvictoria.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    @JsonIgnoreProperties("tables")
    private Waiter waiter;

    @ManyToOne
    @JsonIgnoreProperties("tables")
    private Restaurant restaurant;

    public Table() {
    }

    public Table(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }
}
