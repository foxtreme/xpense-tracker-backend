package com.ccharry.xpensetracker.repository;

import com.ccharry.xpensetracker.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
