package com.ccharry.xpensetracker.repository;

import java.util.List;
import java.util.Optional;

import com.ccharry.xpensetracker.entity.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByName(String name);
    List<Expense> findAllByOrderByNameAsc();
    List<Expense> findAllByFrequency(String frequency);
    List<Expense> findAllByType(String type);
}
