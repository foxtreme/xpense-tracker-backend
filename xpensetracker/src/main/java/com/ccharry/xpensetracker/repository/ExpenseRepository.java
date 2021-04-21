package com.ccharry.xpensetracker.repository;

import com.ccharry.xpensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
