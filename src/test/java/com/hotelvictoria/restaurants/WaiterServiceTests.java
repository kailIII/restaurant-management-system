package com.hotelvictoria.restaurants;

import com.hotelvictoria.restaurants.exceptions.MaxAllowedTablesAssignedException;
import com.hotelvictoria.restaurants.models.Restaurant;
import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.repositories.RestaurantRepository;
import com.hotelvictoria.restaurants.repositories.TableRepository;
import com.hotelvictoria.restaurants.repositories.WaiterRepository;
import com.hotelvictoria.restaurants.services.TableService;
import com.hotelvictoria.restaurants.services.WaiterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaiterServiceTests {
    @Autowired
    WaiterService waiterService;

    @Autowired
    TableService tableService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    WaiterRepository waiterRepository;

    @Autowired
    TableRepository tableRepository;

    @Test
    public void testGetAvailableWaiters() {
        Restaurant restaurant = restaurantRepository.save(new Restaurant("Test Restaurant"));
        Waiter waiter = waiterService.addWaiter("Test Waiter");

        ArrayList<UUID> tableIds = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Table table = tableRepository.save(new Table("Test Table " + i, restaurantRepository.findOne(restaurant.getId())));
            tableIds.add(table.getId());
        }

        tableIds.forEach(tableId -> {
            try {
                tableService.assignWaiterToTable(waiterRepository.findOne(waiter.getId()), tableRepository.findOne(tableId));
            } catch (MaxAllowedTablesAssignedException ignored) {}
        });

        // Sanity check to see if waiter has the correct amount of assigned
        // tables
        Assert.assertEquals(3, waiterRepository.findOne(waiter.getId()).getTables().size());

        // Check that if waiterService::getAvailableWaiters is returning the
        // waiter if they haven't yet reached the max allowed table assignment
        // limit
        Assert.assertEquals(1, waiterService.getAvailableWaiters(restaurant).stream().filter(availableWaiter -> availableWaiter.getId().equals(waiter.getId())).count());

        Table table = tableRepository.save(new Table("Table Table 4", restaurant));

        try {
            tableService.assignWaiterToTable(waiterRepository.findOne(waiter.getId()), table);
        } catch (MaxAllowedTablesAssignedException ignored) {}

        // Sanity check to see if waiter has the correct amount of assigned
        // tables
        Assert.assertEquals(4, waiterRepository.findOne(waiter.getId()).getTables().size());

        // Check that if waiterService::getAvailableWaiters is returning the
        // waiter if they haven't yet reached the max allowed table assignment
        // limit
        Assert.assertEquals(0, waiterService.getAvailableWaiters(restaurant).stream().filter(availableWaiter -> availableWaiter.getId().equals(waiter.getId())).count());
    }
}
