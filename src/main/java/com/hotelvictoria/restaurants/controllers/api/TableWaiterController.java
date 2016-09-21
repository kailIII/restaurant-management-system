package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TableWaiterController {
    @Autowired
    TableRepository tableRepository;

    @GetMapping("/api/v1/tables/{tableId}/waiter")
    public Waiter getTableWaiter(@PathVariable("tableId") Table table) {
        return table.getWaiter();
    }

    @PutMapping("/api/v1/tables/{tableId}/waiter")
    public Waiter putTableWaiter(@PathVariable("tableId") Table table, @RequestParam("waiter_id") Waiter waiter) {
        table.setWaiter(waiter);
        return tableRepository.save(table).getWaiter();
    }
}
