package com.learning.ems.repository;

import com.learning.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //provides CRUD methods on the Employee JPA entity
}
