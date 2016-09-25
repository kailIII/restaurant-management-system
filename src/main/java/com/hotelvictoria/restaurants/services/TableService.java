package com.hotelvictoria.restaurants.services;

import com.hotelvictoria.restaurants.exceptions.MaxAllowedTablesAssignedException;
import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;

    public Waiter getAssignedWaiter(Table table) {
        return table.getWaiter();
    }

    /**
     * Assign a waiter to a table. A waiter can only be assigned to 4 tables for
     * each restaurant.
     */
    public Waiter assignWaiterToTable(Waiter waiter, Table table) throws MaxAllowedTablesAssignedException {
        // This is a validation check to see if a waiter has already been
        // assigned to the maximum number of tables for the restaurant in
        // question. The limit is 4.
        UUID tableRestaurantId = table.getRestaurant().getId();
        int restaurantTableCount = (int) waiter.getTables().stream().filter(currentTable -> currentTable.getRestaurant().getId() == tableRestaurantId).count();
        if (restaurantTableCount >= 4) {
            throw new MaxAllowedTablesAssignedException();
        }

        table.setWaiter(waiter);
        return tableRepository.save(table).getWaiter();
    }

    /**
     * Unassign a waiter from a table.
     */
    public void removeWaiterFromTable(Table table) {
        table.setWaiter(null);
        tableRepository.save(table);
    }
}
