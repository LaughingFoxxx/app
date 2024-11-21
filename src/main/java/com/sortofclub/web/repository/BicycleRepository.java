package com.sortofclub.web.repository;

import com.sortofclub.web.models.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
    Optional<Bicycle> findByName(String name);
}
