package com.ccharry.xpensetracker.repository;

import com.ccharry.xpensetracker.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

}
