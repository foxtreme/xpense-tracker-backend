package com.ccharry.xpensetracker.repository;

import java.util.Optional;

import com.ccharry.xpensetracker.entity.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
}
