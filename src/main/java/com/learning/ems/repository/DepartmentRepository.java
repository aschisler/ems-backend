package com.learning.ems.repository;

import com.learning.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //CRUD methods for department Jpa entity

}
