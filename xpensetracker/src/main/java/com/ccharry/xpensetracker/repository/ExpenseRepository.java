package com.ccharry.xpensetracker.repository;

import java.util.Optional;

import com.ccharry.xpensetracker.entity.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByName(String name);
}
