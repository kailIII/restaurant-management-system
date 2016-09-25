package com.hotelvictoria.restaurants;

import com.hotelvictoria.restaurants.exceptions.MaxAllowedTablesAssignedException;
import com.hotelvictoria.restaurants.models.Restaurant;
import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.services.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableServiceTests {
    @Autowired
    TableService tableService;

    /**
     * Test whether waiters who have already been 4 tables per restaurant (as
     * per the requirements, this is the limit) are unable to be assigned to
     * more tables at that restaurant.
     */
    @Test(expected = MaxAllowedTablesAssignedException.class)
    public void testFourTableLimit() throws MaxAllowedTablesAssignedException {
        Restaurant restaurant = new Restaurant("Test Restaurant");
        Waiter waiter = new Waiter("Test Waiter");
        ArrayList<Table> tables = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            tables.add(new Table("Test Table " + i, restaurant));
        }
        waiter.setTables(tables);

        tableService.assignWaiterToTable(waiter, new Table("Test Table 5", restaurant));
    }
}
