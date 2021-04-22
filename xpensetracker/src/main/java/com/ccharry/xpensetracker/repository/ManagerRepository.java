package com.ccharry.xpensetracker.repository;

import java.util.Optional;

import com.ccharry.xpensetracker.entity.Manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager>findByName(String name);
}
