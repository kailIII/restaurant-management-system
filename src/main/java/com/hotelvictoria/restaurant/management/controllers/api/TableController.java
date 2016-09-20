package com.hotelvictoria.restaurant.management.controllers.api;

import com.hotelvictoria.restaurant.management.models.Table;
import com.hotelvictoria.restaurant.management.models.Waiter;
import com.hotelvictoria.restaurant.management.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TableController {
    @Autowired
    TableRepository tableRepository;

    @GetMapping("/api/v1/tables")
    public Iterable<Table> getTables() {
        return tableRepository.findAll();
    }

    @GetMapping("/api/v1/tables/{tableId}")
    public Table getTable(@PathVariable("tableId") UUID tableId) {
        return tableRepository.findOne(tableId);
    }

    @PostMapping("/api/v1/tables")
    public Table postTable() {
        Table table = new Table("Table 1");

        return tableRepository.save(table);
    }
}
