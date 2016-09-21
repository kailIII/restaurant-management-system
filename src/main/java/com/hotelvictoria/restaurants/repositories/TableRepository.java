package com.hotelvictoria.restaurants.repositories;

import com.hotelvictoria.restaurants.models.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TableRepository extends CrudRepository<Table, UUID> {
}
