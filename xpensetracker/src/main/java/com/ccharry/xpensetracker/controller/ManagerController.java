package com.ccharry.xpensetracker.controller;

import java.util.List;

import javax.validation.Valid;

import com.ccharry.xpensetracker.entity.Manager;
import com.ccharry.xpensetracker.repository.ManagerRepository;
import com.ccharry.xpensetracker.service.ManagerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {
    
    private ManagerService managerService;
    private ManagerRepository managerRepository;

    public ManagerController(ManagerService managerService, ManagerRepository managerRepository) {
        this.managerService = managerService;
        this.managerRepository = managerRepository;
    }

    
    /** 
     * @param manager
     * @return ResponseEntity<Object>
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createManager(@Valid @RequestBody Manager manager) {
        return managerService.createManager(manager);
    }

    
    /** 
     * @param id
     * @return Manager
     */
    @GetMapping("/details/{id}")
    public Manager getManager(@PathVariable Long id) {
        if(managerRepository.findById(id).isPresent())
            return managerRepository.findById(id).get();
        else return null;
    }

    /** 
     * @param name
     * @return Manager
     */
    @GetMapping("/details/name")
    public Manager getManagerByName(@RequestParam(name= "name") String name) {
        if(managerRepository.findByName(name).isPresent())
            return managerRepository.findByName(name).get();
        else return null;
    }

    
    /** 
     * @return List<Manager>
     */
    @GetMapping("/all")
    public List<Manager> all() {
        return managerRepository.findAllByOrderByNameAsc();
        
    }

    /**
     * Returns all managers ordered by how many expenses they have 
     * @return
     */
    @GetMapping("/all/expenses")
    public List<Object> allByExpenses() {
        return managerRepository.findAllByExpenses();
    }

    /** 
     * @return List<City>
     */
    @GetMapping("/all/city")
    List<Manager> getCityManagers(@RequestParam String city){
        return managerRepository.findAllCitiesManagers(city);
    }
    
    /** 
     * @param id
     * @param manager
     * @return ResponseEntity<Object>
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateManager(@PathVariable Long id, @Valid @RequestBody Manager manager) {
        return managerService.updateManager(manager, id);
    }

    
    /** 
     * @param id
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteManager(@PathVariable Long id) {
        return managerService.deleteManager(id);
    }
}
