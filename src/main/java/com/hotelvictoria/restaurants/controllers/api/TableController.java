package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.models.Restaurant;
import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.repositories.TableRepository;
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
    public Table postTables(@RequestParam("name") String tableName, @RequestParam("restaurant_id") Restaurant restaurant) {
        return tableRepository.save(new Table(tableName, restaurant));
    }
}
