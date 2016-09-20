package com.hotelvictoria.restaurant.management.repositories;

import com.hotelvictoria.restaurant.management.models.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, UUID> {
}
