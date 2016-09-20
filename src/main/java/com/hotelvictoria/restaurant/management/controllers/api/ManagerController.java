package com.hotelvictoria.restaurant.management.controllers.api;

import com.hotelvictoria.restaurant.management.models.Manager;
import com.hotelvictoria.restaurant.management.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
    @Autowired
    ManagerRepository managerRepository;

    @GetMapping("/api/v1/managers")
    public Iterable<Manager> getManagers() {
        return managerRepository.findAll();
    }

    @PostMapping("/api/v1/managers")
    public Manager postManager() {
        Manager manager = new Manager("Steve the Manager");

        return managerRepository.save(manager);
    }
}
