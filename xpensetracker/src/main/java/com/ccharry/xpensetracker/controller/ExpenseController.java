package com.ccharry.xpensetracker.controller;

import com.ccharry.xpensetracker.entity.Expense;
import com.ccharry.xpensetracker.repository.ExpenseRepository;
import com.ccharry.xpensetracker.service.ExpenseService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ExpenseController {

    private ExpenseService expenseService;
    private ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseService expenseService, ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
        this.expenseRepository = expenseRepository;
    }

    
    /** 
     * @param expense
     * @return ResponseEntity<Object>
     */
    @PostMapping("/expense/create")
    public ResponseEntity<Object> createExpense(@RequestBody Expense expense){
        return expenseService.addExpense(expense);
    }

    
    /** 
     * @param id
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/expense/delete/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }

    
    /** 
     * @param id
     * @return Expense
     */
    @GetMapping("/expense/details/{id}")
    public Expense getExpense(@PathVariable Long id) {
        if (expenseRepository.findById(id).isPresent())
            return expenseRepository.findById(id).get();
        else return null;
    }

    
    /** 
     * @return List<Expense>
     */
    @GetMapping("/expense/all")
    public List<Expense> getExpenses(){
        return expenseRepository.findAll();
    }    

    
    /** 
     * @param id
     * @param expense
     * @return ResponseEntity<Object>
     */
    @PutMapping("/expense/update/{id}")
    public ResponseEntity<Object> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }
    
}
