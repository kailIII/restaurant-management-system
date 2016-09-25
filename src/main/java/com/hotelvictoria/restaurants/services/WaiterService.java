package com.hotelvictoria.restaurants.services;

import com.hotelvictoria.restaurants.models.Restaurant;
import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.repositories.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class WaiterService {
    @Autowired
    WaiterRepository waiterRepository;

    /**
     * Get all of the waiters.
     */
    public Iterable<Waiter> getAvailableWaiters() {
        return waiterRepository.findAll();
    }

    /**
     * Get all of the waiters that are still eligible to be assigned to the
     * current restaurants. Waiters are disqualified if they have already been
     * assigned to 4 tables in that restaurant.
     */
    public List<Waiter> getAvailableWaiters(Restaurant restaurant) {
        return StreamSupport.stream(waiterRepository.findAll().spliterator(), false)
                // Filter out all the waiters who have reached the 4 table limit
                // per restaurant
                .filter(waiter -> getWaiterTablesForRestaurant(waiter, restaurant).count() < 4)
                .collect(Collectors.toList());
    }

    /**
     * Get all of the tables belonging to a particular restaurant that the waiter
     * has been assigned to.
     */
    public Stream<Table> getWaiterTablesForRestaurant(Waiter waiter, Restaurant restaurant) {
        return waiter.getTables().stream().filter(table -> table.getRestaurant().getId().equals(restaurant.getId()));
    }

    /**
     * Add a new waiter to the database
     */
    public Waiter addWaiter(String name) {
        return waiterRepository.save(new Waiter(name));
    }
}
