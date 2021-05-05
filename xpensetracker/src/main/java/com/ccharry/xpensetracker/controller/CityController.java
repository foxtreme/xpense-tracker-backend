package com.ccharry.xpensetracker.controller;

import java.util.List;

import javax.validation.Valid;

import com.ccharry.xpensetracker.repository.CityRepository;
import com.ccharry.xpensetracker.service.CityService;
import com.ccharry.xpensetracker.entity.City;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/city")
public class CityController {
    
    private CityService cityService;
    private CityRepository cityRepository;

    public CityController(CityService cityService, CityRepository cityRepository){
        this.cityService = cityService;
        this.cityRepository = cityRepository;
    }

    
    /** 
     * @param city
     * @return ResponseEntity<Object>
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createCity(@Valid @RequestBody City city){
        return cityService.createCity(city);
    }

    
    /** 
     * @param id
     * @return City
     */
    @GetMapping("/details/{id}")
    public City getCity(@PathVariable Long id) {
        if(cityRepository.findById(id).isPresent())
            return cityRepository.findById(id).get();
        else return null;
    }

    
    /** 
     * @return List<City>
     */
    @GetMapping("/all")
    List<City> getCities(){
        return cityRepository.findAll();
    }

    
    /** 
     * @param id
     * @param city
     * @return ResponseEntity<Object>
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCity(@PathVariable Long id, @Valid @RequestBody City city) {
        return cityService.updateCity(city, id);
    }

    
    /** 
     * @param id
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCity(@PathVariable Long id){
        return cityService.deleteCity(id);
    }
}
