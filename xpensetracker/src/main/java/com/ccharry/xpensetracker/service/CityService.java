package com.ccharry.xpensetracker.service;

import com.ccharry.xpensetracker.entity.City;
import com.ccharry.xpensetracker.repository.CityRepository;
import com.ccharry.xpensetracker.repository.ExpenseRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {
    private CityRepository cityRepository;
    private ExpenseRepository expenseRepository;

    public CityService(CityRepository cityRepository, ExpenseRepository expenseRepository) {
        this.cityRepository = cityRepository;
        this.expenseRepository = expenseRepository;
    }

    /** Create a new City */
    public ResponseEntity<Object> createCity(City model){
        City city = new City();
        if (cityRepository.findByName(model.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("The City already exists, Failed to create new City");
        } else {
            city.setName(model.getName());
            city.setDescription(model.getDescription());
            city.setCountry(model.getCountry());
            city.setExpenses(model.getExpenses());

            City savedCity = cityRepository.save(city);
            if (cityRepository.findById(savedCity.getId()).isPresent())
                return ResponseEntity.ok("City created successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed creating city as specified");
        }
    }

    /** Update a City */
    @Transactional
    public ResponseEntity<Object> updateCity(City city, Long id) {
        if (cityRepository.findById(id).isPresent()) {
            City newCity = cityRepository.findById(id).get();
            newCity.setName(city.getName());
            newCity.setDescription(city.getDescription());
            newCity.setCountry(city.getCountry());
            newCity.setExpenses(city.getExpenses());

            City savedCity = cityRepository.save(newCity);
            if(cityRepository.findById(savedCity.getId()).isPresent())
                return ResponseEntity.accepted().body("City updated Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the city specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the city specified");
    }

    /** Delete a City */
    public ResponseEntity<Object> deleteCity(Long id) {
        if (cityRepository.findById(id).isPresent()) {
            cityRepository.deleteById(id);
            if (cityRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified city");
            else return ResponseEntity.ok().body("Successfully delete the specified city");
        } else return ResponseEntity.badRequest().body("Cannot find the specified city");
    }
}
