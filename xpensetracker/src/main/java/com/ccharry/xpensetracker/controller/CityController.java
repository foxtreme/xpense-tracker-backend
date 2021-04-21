package com.ccharry.xpensetracker.controller;

import java.util.List;
import com.ccharry.xpensetracker.model.City;
import com.ccharry.xpensetracker.model.Expense;
import com.ccharry.xpensetracker.repository.CityRepository;
import com.ccharry.xpensetracker.repository.ExpenseRepository;
import com.ccharry.xpensetracker.errors.CityNotFoundException;
import com.ccharry.xpensetracker.errors.ExpenseNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CityController {
    
    private final CityRepository repository;
    private final ExpenseRepository expenseRepository;

    public CityController(CityRepository repository, ExpenseRepository expenseRepository){
        this.repository = repository;
        this.expenseRepository = expenseRepository;
    }

    // aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/cities")
    List<City> all(){
        return repository.findAll();
    }
    //end::get-aggregate-root[]

    @PostMapping("/cities")
    City newCity(@RequestBody City newCity){
        return repository.save(newCity);
    }

    // Single item

    @GetMapping("/cities/{id}")
    City one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new CityNotFoundException(id));
    }

    @PutMapping("/cities/{id}")
    City replaceCity(@RequestBody City newCity, @PathVariable Long id) {
        City city = repository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
        city.setName(newCity.getName());
        city.setDescription(newCity.getDescription());
        city.setCountry(newCity.getCountry());
        city.setExpenses(newCity.getExpenses());
        final City updatedCity = repository.save(city);
        return updatedCity;
    }

    @PutMapping("/cities/{cityId}/expenses/{expenseId}")
    City updateCity(@PathVariable Long cityId, @PathVariable Long expenseId) {
        City city = repository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException(expenseId));
        List<Expense> expenses = city.getExpenses();
        expenses.add(expense);
        city.setExpenses(expenses);
        return repository.save(city);
    }

    @DeleteMapping("/cities/{id}")
    void deleteCity(@PathVariable Long id){
        repository.deleteById(id);
    }

}
