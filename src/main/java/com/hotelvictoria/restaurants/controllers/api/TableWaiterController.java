package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TableWaiterController {
    @Autowired
    TableRepository tableRepository;

    @GetMapping("/api/v1/tables/{tableId}/waiter")
    public Waiter getTableWaiter(@PathVariable("tableId") Table table) {
        return table.getWaiter();
    }

    @PostMapping("/api/v1/tables/{tableId}/waiter")
    public Waiter postTableWaiter(@PathVariable("tableId") Table table, @RequestParam("waiter_id") Waiter waiter) {
        table.setWaiter(waiter);
        return tableRepository.save(table).getWaiter();
    }

    @DeleteMapping("/api/v1/tables/{tableId}/waiter")
    public ResponseEntity<?> deleteTableWaiter(@PathVariable("tableId") Table table) {
        table.setWaiter(null);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
