package com.ccharry.xpensetracker.service;

import com.ccharry.xpensetracker.entity.Manager;
import com.ccharry.xpensetracker.repository.ExpenseRepository;
import com.ccharry.xpensetracker.repository.ManagerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {
    
    private ManagerRepository managerRepository;
    private ExpenseRepository expenseRepository;

    public ManagerService(ManagerRepository managerRepository, ExpenseRepository expenseRepository) {
        this.managerRepository = managerRepository;
        this.expenseRepository = expenseRepository;
    }

    /** Create a new Manager */
    public ResponseEntity<Object> createManager(Manager model) {
        Manager manager = new Manager();
        if (managerRepository.findByName(model.getName()).isPresent()) 
            return ResponseEntity.badRequest().body("The Manager already exist, Failed to create new Manager");
        else {
            manager.setName(model.getName());
            manager.setExpenses(model.getExpenses());
            
            Manager savedManager = managerRepository.save(manager);
            if(managerRepository.findById(savedManager.getId()).isPresent())
                return ResponseEntity.ok().body(savedManager);
            else return ResponseEntity.unprocessableEntity().body("Failed creating Manager as Specified");
        }
    }

    /** Update a Manager */
    @Transactional
    public ResponseEntity<Object> updateManager(Manager manager, Long id) {
        if(managerRepository.findById(id).isPresent()){
            Manager newManager = managerRepository.findById(id).get();
            newManager.setName(manager.getName());
            newManager.setExpenses(manager.getExpenses());
            Manager savedManager = managerRepository.save(newManager);
            if(managerRepository.findById(savedManager.getId()).isPresent()) 
                return ResponseEntity.accepted().body(savedManager);
            else return ResponseEntity.unprocessableEntity().body("Failed updating the specified Manager");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the specified Manager");
    }

    /** Delete a Manager */
    public ResponseEntity<Object> deleteManager(Long id){
        if(managerRepository.findById(id).isPresent()){
            managerRepository.deleteById(id);
            if(managerRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified Manager");
            else return ResponseEntity.noContent().build();
        } else return ResponseEntity.badRequest().body("Cannot find the Manager specified");
    }
}
