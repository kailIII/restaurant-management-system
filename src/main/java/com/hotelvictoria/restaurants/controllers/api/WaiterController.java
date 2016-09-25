package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.services.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WaiterController {
    @Autowired
    WaiterService waiterService;

    @GetMapping("/api/v1/waiters")
    public Iterable<Waiter> getWaiters(@RequestParam(name = "table_id", required = false) Table table) {
        if (table == null) {
            return waiterService.getAvailableWaiters();
        }
        return waiterService.getAvailableWaiters(table.getRestaurant());
    }

    @GetMapping("api/v1/waiters/{waiterId}")
    public Waiter getWaiter(@PathVariable("waiterId") Waiter waiter) {
        return waiter;
    }

    @PostMapping("/api/v1/waiters")
    public Waiter postWaiter(@RequestParam("name") String name) {
        return waiterService.addWaiter(name);
    }
}
