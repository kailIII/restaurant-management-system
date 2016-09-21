package com.hotelvictoria.restaurants.repositories;

import com.hotelvictoria.restaurants.models.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, UUID> {
}
