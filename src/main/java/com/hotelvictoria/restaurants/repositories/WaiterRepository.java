package com.hotelvictoria.restaurants.repositories;

import com.hotelvictoria.restaurants.models.Waiter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WaiterRepository extends CrudRepository<Waiter, UUID> {

}
