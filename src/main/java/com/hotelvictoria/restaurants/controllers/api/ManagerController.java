package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.models.Manager;
import com.hotelvictoria.restaurants.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerController {
    @Autowired
    ManagerRepository managerRepository;

    @GetMapping("/api/v1/managers")
    public Iterable<Manager> getManagers() {
        return managerRepository.findAll();
    }

    @GetMapping("/api/v1/managers/{managerId}")
    public Manager getManager(@PathVariable("managerId") Manager manager) {
        return manager;
    }

    @PostMapping("/api/v1/managers")
    public Manager postManager(@RequestParam("name") String name) {
        return managerRepository.save(new Manager(name));
    }
}
