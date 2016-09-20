package com.hotelvictoria.restaurant.management.repositories;

import com.hotelvictoria.restaurant.management.models.Waiter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WaiterRepository extends CrudRepository<Waiter, UUID> {
}
