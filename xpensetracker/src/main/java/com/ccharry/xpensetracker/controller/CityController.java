package com.ccharry.xpensetracker.controller;

import java.util.List;

import com.ccharry.xpensetracker.repository.CityRepository;
import com.ccharry.xpensetracker.service.CityService;
import com.ccharry.xpensetracker.entity.City;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CityController {
    
    private CityService cityService;
    private CityRepository cityRepository;

    public CityController(CityService cityService, CityRepository cityRepository){
        this.cityService = cityService;
        this.cityRepository = cityRepository;
    }

    @PostMapping("/city/create")
    public ResponseEntity<Object> createCity(@RequestBody City city){
        return cityService.createCity(city);
    }

    @GetMapping("/city/details/{id}")
    public City getCity(@PathVariable Long id) {
        if(cityRepository.findById(id).isPresent())
            return cityRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/city/all")
    List<City> getCities(){
        return cityRepository.findAll();
    }

    @PutMapping("/city/update/{id}")
    public ResponseEntity<Object> updateCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.updateCity(city, id);
    }

    @DeleteMapping("/city/delete/{id}")
    public ResponseEntity<Object> deleteCity(@PathVariable Long id){
        return cityService.deleteCity(id);
    }
}
