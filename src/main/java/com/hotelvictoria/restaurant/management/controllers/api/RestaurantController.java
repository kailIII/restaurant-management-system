package com.hotelvictoria.restaurant.management.controllers.api;

import com.hotelvictoria.restaurant.management.models.Restaurant;
import com.hotelvictoria.restaurant.management.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/api/v1/restaurants")
    public Iterable<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @PostMapping("/api/v1/restaurants")
    public Restaurant postRestaurant() {
        Restaurant restaurant = new Restaurant("Luigi's Diner");

        return restaurantRepository.save(restaurant);
    }
}
