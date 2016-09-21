package com.hotelvictoria.restaurants.repositories;

import com.hotelvictoria.restaurants.models.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, UUID> {
}
