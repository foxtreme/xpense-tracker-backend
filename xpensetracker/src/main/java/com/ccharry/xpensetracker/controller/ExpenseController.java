package com.ccharry.xpensetracker.controller;

import com.ccharry.xpensetracker.errors.ExpenseNotFoundException;
import com.ccharry.xpensetracker.model.Expense;
import com.ccharry.xpensetracker.repository.ExpenseRepository;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ExpenseController {

    private final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    // aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/expenses")
    List<Expense> all(){
        return repository.findAll();
    }
    //end::get-aggregate-root[]

    @PostMapping("/expenses")
    Expense newExpense(@RequestBody Expense newExpense){
        return repository.save(newExpense);
    }

    // Single item
    
    @GetMapping("/expenses/{id}")
    Expense one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    @PutMapping("/expenses/{id}")
    Expense replaceExpense(@RequestBody Expense newExpense, @PathVariable Long id) {
        Expense expense = repository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));
        expense.setName(newExpense.getName());
        expense.setType(newExpense.getType());
        expense.setFrequency(newExpense.getFrequency());
        expense.setMinValue(newExpense.getMinValue());
        expense.setMaxValue(newExpense.getMaxValue());
        expense.setCities(newExpense.getCities());
        expense.setManagers(newExpense.getManagers());
        
        final Expense updatedExpense = repository.save(expense);
        return updatedExpense;
    }

    @DeleteMapping("/expenses/{id}")
    void deleteExpense(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
