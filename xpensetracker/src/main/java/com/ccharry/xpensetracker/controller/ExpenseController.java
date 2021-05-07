package com.ccharry.xpensetracker.controller;

import com.ccharry.xpensetracker.entity.Expense;
import com.ccharry.xpensetracker.repository.ExpenseRepository;
import com.ccharry.xpensetracker.service.ExpenseService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private ExpenseService expenseService;
    private ExpenseRepository expenseRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    public ExpenseController(ExpenseService expenseService, ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
        this.expenseRepository = expenseRepository;
    }
    
    /** 
     * @param expense
     * @return ResponseEntity<Object>
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createExpense(@Valid @RequestBody Expense expense){
        LOGGER.info("POST call to Expense API creating Expense with payload: {}", expense);
        return expenseService.addExpense(expense);
    }
    
    /** 
     * @param id
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable Long id) {
        LOGGER.info("DELETE call to Expense API deleting Expense with Id: {}", id);
        return expenseService.deleteExpense(id);
    }
    
    /** 
     * @param id
     * @return Expense
     */
    @GetMapping("/details/{id}")
    public Expense getExpense(@PathVariable Long id) {
        if (expenseRepository.findById(id).isPresent()){
            LOGGER.info("GET call to Expense API getting City with Id: {}", id);
            return expenseRepository.findById(id).get();
        }
        else return null;
    }

    @GetMapping("/details/frequency")
    public List<Expense> getExpensesByFrequency(@RequestParam String frequency) {
        LOGGER.info("GET call to Expense API getting all Expenses by Frequency: {}", frequency);
        return expenseRepository.findAllByFrequency(frequency);
    }

    @GetMapping("/details/type")
    public List<Expense> getExpensesByType(@RequestParam String type) {
        LOGGER.info("GET call to Expense API getting all Expenses by Type: {}", type);
        return expenseRepository.findAllByType(type);
    }
    
    /** 
     * @return List<Expense>
     */
    @GetMapping("/all")
    public List<Expense> getExpenses(){
        LOGGER.info("GET call to Expense API getting all Expenses ordered by Name asc");
        return expenseRepository.findAllByOrderByNameAsc();
    }    

    /** 
     * @param id
     * @param expense
     * @return ResponseEntity<Object>
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expense) {
        LOGGER.info("PUT call to Expense API updating Expense with Id: %d and payload: %s", id, expense);
        return expenseService.updateExpense(id, expense);
    }
}
