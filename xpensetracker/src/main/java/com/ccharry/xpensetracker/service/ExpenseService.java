package com.ccharry.xpensetracker.service;

import java.util.ArrayList;

import com.ccharry.xpensetracker.entity.City;
import com.ccharry.xpensetracker.entity.Expense;
import com.ccharry.xpensetracker.entity.Manager;
import com.ccharry.xpensetracker.repository.CityRepository;
import com.ccharry.xpensetracker.repository.ExpenseRepository;
import com.ccharry.xpensetracker.repository.ManagerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService {
   
    private CityRepository cityRepository;
    private ExpenseRepository expenseRepository;
    private ManagerRepository managerRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CityRepository cityRepository, ManagerRepository managerRepository){
        this.cityRepository = cityRepository;
        this.expenseRepository = expenseRepository;
        this.managerRepository = managerRepository;
    }

    /** Create a new Expense */
    @Transactional
    public ResponseEntity<Object> addExpense(Expense expense) {
        Expense newExpense = new Expense();
        newExpense.setName(expense.getName());
        newExpense.setFrequency(expense.getFrequency());
        newExpense.setType(expense.getType());
        newExpense.setMaxValue(expense.getMaxValue());
        newExpense.setMinValue(expense.getMinValue());
        newExpense.setCities(expense.getCities());
        newExpense.setManagers(expense.getManagers());

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(newExpense);
        if (expense.getCities() != null){
            for (int i=0; i< expense.getCities().size(); i++) {
                if(!cityRepository.findByName(expense.getCities().get(i).getName()).isPresent()) {
                    City newCity = expense.getCities().get(i);
                    newCity.setExpenses(expenseList);
                    City savedCity = cityRepository.save(newCity);
                    if(! cityRepository.findById(savedCity.getId()).isPresent())
                        return ResponseEntity.unprocessableEntity().body("City creation failed");
                } else return ResponseEntity.unprocessableEntity().body("City with the same name is already present"); 
            }
        }
        if (expense.getManagers() != null) {
            for (int i=0; i< expense.getManagers().size(); i++) {
                if(!cityRepository.findByName(expense.getManagers().get(i).getName()).isPresent()) {
                    Manager newManager = expense.getManagers().get(i);
                    newManager.setExpenses(expenseList);
                    Manager savedManager = managerRepository.save(newManager);
                    if(! managerRepository.findById(savedManager.getId()).isPresent())
                        return ResponseEntity.unprocessableEntity().body("Manager creation failed");
                } else return ResponseEntity.unprocessableEntity().body("Manager with the same name is already present"); 
            }
        }

        if (newExpense.getCities() == null && newExpense.getManagers() == null){
            expenseRepository.save(newExpense);
        }
        
        return ResponseEntity.ok().body(newExpense);
    }

    /** Delete an Expense */
    public ResponseEntity<Object> deleteExpense(Long id) {
        if(expenseRepository.findById(id).isPresent()){
            expenseRepository.deleteById(id);
            if(expenseRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            else return ResponseEntity.noContent().build();
        } else 
            return ResponseEntity.unprocessableEntity().body("No records found");
    }

    /** Update an Expense */
    @Transactional
    public ResponseEntity<Object> updateExpense(Long id, Expense expense){
        if(expenseRepository.findById(id).isPresent()){
            Expense newExpense = expenseRepository.findById(id).get();
            newExpense.setName(expense.getName());
            newExpense.setType(expense.getType());
            newExpense.setFrequency(expense.getFrequency());
            newExpense.setMaxValue(expense.getMaxValue());
            newExpense.setMinValue(expense.getMinValue());
            newExpense.setManagers(expense.getManagers());
            newExpense.setCities(expense.getCities());
            Expense savedExpense = expenseRepository.save(newExpense);
            if(expenseRepository.findById(savedExpense.getId()).isPresent())
                return ResponseEntity.accepted().body(savedExpense);
            else return ResponseEntity.badRequest().body("Failed to update Expense");
        } else return ResponseEntity.unprocessableEntity().body("Specified Expense not found");
    }
}
