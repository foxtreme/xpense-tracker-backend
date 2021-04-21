package com.ccharry.xpensetracker.controller;

import java.util.List;
import com.ccharry.xpensetracker.errors.ManagerNotFoundException;
import com.ccharry.xpensetracker.model.Manager;
import com.ccharry.xpensetracker.repository.ManagerRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
    
    private final ManagerRepository repository;

    public ManagerController(ManagerRepository repository) {
        this.repository = repository;
    }

    // aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/managers")
    List<Manager> all() {
        return repository.findAll();
    }
    //end::get-aggregate-root[]

    @PostMapping("/managers")
    Manager newManager(@RequestBody Manager newManager) {
        return repository.save(newManager);
    }

    // Single item

    @GetMapping("/managers/{id}")
    Manager one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new ManagerNotFoundException(id));
    }

    @PutMapping("/managers/{id}")
    Manager replaceManager(@RequestBody Manager newManager, @PathVariable Long id) {
        Manager manager = repository.findById(id).orElseThrow(() -> new ManagerNotFoundException(id));
        manager.setName(newManager.getName());
        manager.setExpenses(newManager.getExpenses());
        final Manager updatedManager = repository.save(manager);
        return updatedManager;
    }

    @DeleteMapping("/managers/{id}")
    void deleteManager(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
