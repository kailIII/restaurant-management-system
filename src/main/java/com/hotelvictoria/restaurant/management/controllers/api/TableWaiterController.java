package com.hotelvictoria.restaurant.management.controllers.api;

import com.hotelvictoria.restaurant.management.models.Table;
import com.hotelvictoria.restaurant.management.models.Waiter;
import com.hotelvictoria.restaurant.management.repositories.TableRepository;
import com.hotelvictoria.restaurant.management.repositories.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
public class TableWaiterController {
    @Autowired
    WaiterRepository waiterRepository;

    @GetMapping("/api/v1/tables/{tableId}/waiter")
    public ResponseEntity<?> getTableWaiter(@PathVariable("tableId") Table table) {
        if (table == null || table.getWaiter() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(table.getWaiter(), HttpStatus.OK);
    }

    @PostMapping("/api/v1/tables/{tableId}/waiter")
    public ResponseEntity<?> postTableWaiter(@PathVariable("tableId") Table table, @RequestParam("waiter_id") Waiter waiter) {
        if (table == null || waiter == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Collection<Table> tables = waiter.getTables();
        table.setWaiter(waiter);
        tables.add(table);

        waiter.setTables(tables);
        waiterRepository.save(waiter);

        return new ResponseEntity<>(waiter, HttpStatus.OK);
    }
}
