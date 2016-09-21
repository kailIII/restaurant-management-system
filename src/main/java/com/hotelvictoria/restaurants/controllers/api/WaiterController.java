package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.repositories.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaiterController {
    @Autowired
    WaiterRepository waiterRepository;

    @GetMapping("/api/v1/waiters")
    public Iterable<Waiter> getWaiters() {
        return waiterRepository.findAll();
    }

    @PostMapping("/api/v1/waiters")
    public Waiter postWaiter(@RequestParam("name") String name) {
        return waiterRepository.save(new Waiter(name));
    }
}
