package com.ccharry.xpensetracker.repository;

import java.util.List;
import java.util.Optional;

import com.ccharry.xpensetracker.entity.Manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager>findByName(String name);
    List<Manager>findAllByOrderByNameAsc();
    
    @Query(value = "SELECT Managers.name, count (Managers_Expenses.expenses_id) as expenses FROM Managers, Managers_Expenses where Managers_Expenses.managers_id= Managers.id group by managers_id order by expenses DESC", nativeQuery = true)
    List<Object> findAllByExpenses();
    
}
