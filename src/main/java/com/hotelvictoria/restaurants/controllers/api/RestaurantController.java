package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.models.Restaurant;
import com.hotelvictoria.restaurants.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/api/v1/restaurants")
    public Iterable<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/api/v1/restaurants/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable("restaurantId") Restaurant restaurant) {
        return restaurant;
    }

    @PostMapping("/api/v1/restaurants")
    public Restaurant postRestaurant(@RequestParam("name") String name) {
        return restaurantRepository.save(new Restaurant(name));
    }
}
