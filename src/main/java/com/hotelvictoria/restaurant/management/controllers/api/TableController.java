package com.hotelvictoria.restaurant.management.controllers.api;

import com.hotelvictoria.restaurant.management.models.Table;
import com.hotelvictoria.restaurant.management.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TableController {
    @Autowired
    TableRepository tableRepository;

    @GetMapping("/api/v1/tables")
    public Iterable<Table> getTables() {
        return tableRepository.findAll();
    }

    @GetMapping("/api/v1/tables/{tableId}")
    public Table getTable(@PathVariable("tableId") Table table) {
        return table;
    }

    @PostMapping("/api/v1/tables")
    public Table postTable(@RequestParam("name") String tableName) {
        return tableRepository.save(new Table(tableName));
    }
}
