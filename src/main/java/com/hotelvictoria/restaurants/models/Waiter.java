package com.hotelvictoria.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "waiter")
    @JsonIgnoreProperties("waiter")
    private Collection<Table> tables;

    public Waiter() {
    }

    public Waiter(String name) {
        this.name = name;
    }
}
