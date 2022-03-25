package com.app.employee.api.repo;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.employee.api.beans.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,Integer> {
    List<Employee> findByFirstName(String name);
}