package com.ccharry.xpensetracker.controller;

import java.util.List;

import com.ccharry.xpensetracker.entity.Manager;
import com.ccharry.xpensetracker.repository.ManagerRepository;
import com.ccharry.xpensetracker.service.ManagerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
    
    private ManagerService managerService;
    private ManagerRepository managerRepository;

    public ManagerController(ManagerService managerService, ManagerRepository managerRepository) {
        this.managerService = managerService;
        this.managerRepository = managerRepository;
    }

    @PostMapping("/manager/create")
    public ResponseEntity<Object> createManager(@RequestBody Manager manager) {
        return managerService.createManager(manager);
    }

    @GetMapping("/manager/details/{id}")
    public Manager getManager(@PathVariable Long id) {
        if(managerRepository.findById(id).isPresent())
            return managerRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/manager/all")
    public List<Manager> all() {
        return managerRepository.findAll();
    }

    @PutMapping("/manager/update/{id}")
    public ResponseEntity<Object> updateManager(@PathVariable Long id, @RequestBody Manager manager) {
        return managerService.updateManager(manager, id);
    }

    @DeleteMapping("/managers/delete/{id}")
    public ResponseEntity<Object> deleteManager(@PathVariable Long id) {
        return managerService.deleteManager(id);
    }
}
