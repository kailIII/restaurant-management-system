package com.hotelvictoria.restaurants;

import com.hotelvictoria.restaurants.models.Manager;
import com.hotelvictoria.restaurants.models.Restaurant;
import com.hotelvictoria.restaurants.models.Table;
import com.hotelvictoria.restaurants.models.Waiter;
import com.hotelvictoria.restaurants.repositories.ManagerRepository;
import com.hotelvictoria.restaurants.repositories.RestaurantRepository;
import com.hotelvictoria.restaurants.repositories.TableRepository;
import com.hotelvictoria.restaurants.repositories.WaiterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestaurantsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantsApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedDatabase(ManagerRepository managerRepository, RestaurantRepository restaurantRepository, WaiterRepository waiterRepository, TableRepository tableRepository) {
        return (args) -> {
            // Add 1 manager
            managerRepository.save(new Manager("Nigel Victoria"));

            // Add 2 restaurants
            for (String restaurantName : new String[]{"McDonald's", "KFC"}) {
                Restaurant restaurant = restaurantRepository.save(new Restaurant(restaurantName));
                // Add 20 tables to each restaurant
                for (int i = 1; i <= 20; i++) {
                    tableRepository.save(new Table("Table " + i, restaurant));
                }
            }

            // Add 9 waiters
            for (String name : new String[]{"John", "Taylor", "Fred"}) {
                for (String surname : new String[]{"Smith", "Black", "Brown"}) {
                    waiterRepository.save(new Waiter(name + ' ' + surname));
                }
            }
        };
    }
}
