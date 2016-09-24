package com.hotelvictoria.restaurants.services;

import com.hotelvictoria.restaurants.models.Restaurant;
import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.repositories.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class WaiterService {
    @Autowired
    WaiterRepository waiterRepository;

    public Iterable<Waiter> getAvailableWaiters() {
        return waiterRepository.findAll();
    }

    public Iterable<Waiter> getAvailableWaiters(Restaurant restaurant) {
        return StreamSupport.stream(waiterRepository.findAll().spliterator(), false)
                .filter(waiter -> {
                    return getWaiterTablesForRestaurant(waiter, restaurant).count() < 4;
                })
                .collect(Collectors.toList());
    }

    public Stream<Table> getWaiterTablesForRestaurant(Waiter waiter, Restaurant restaurant) {
        return waiter.getTables().stream().filter(table -> {
            return table.getRestaurant().getId() == restaurant.getId();
        });
    }

    public Waiter addWaiter(String name) {
        return waiterRepository.save(new Waiter(name));
    }
}
