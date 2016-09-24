package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.exceptions.MaxAllowedTablesAssignedException;
import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TableWaiterController {
    @Autowired
    TableService tableService;

    @GetMapping("/api/v1/tables/{tableId}/waiter")
    public Waiter getTableWaiter(@PathVariable("tableId") Table table) {
        return tableService.getAssignedWaiter(table);
    }

    @PostMapping("/api/v1/tables/{tableId}/waiter")
    public Waiter postTableWaiter(@PathVariable("tableId") Table table, @RequestParam("waiter_id") Waiter waiter) throws Exception {
        return tableService.assignWaiterToTable(waiter, table);
    }

    @DeleteMapping("/api/v1/tables/{tableId}/waiter")
    public ResponseEntity<?> deleteTableWaiter(@PathVariable("tableId") Table table) {
        tableService.removeWaiterFromTable(table);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MaxAllowedTablesAssignedException.class)
    public ResponseEntity<?> handleMaxAllowedTablesAssignedException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
