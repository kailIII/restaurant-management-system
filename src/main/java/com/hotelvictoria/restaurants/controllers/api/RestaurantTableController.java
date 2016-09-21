package com.hotelvictoria.restaurants.controllers.api;

import com.hotelvictoria.restaurants.models.Restaurant;
import com.hotelvictoria.restaurants.models.Table;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantTableController {
    @GetMapping("/api/v1/restaurants/{restaurantId}/tables")
    public Iterable<Table> getRestaurantTables(@PathVariable("restaurantId") Restaurant restaurant) {
        return restaurant.getTables();
    }
}
