package com.app.employee.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.employee.api.beans.Employee;
import com.app.employee.api.exception.EmployeeNotFoundException;
import com.app.employee.api.service.EmployeeService;

/**
 * 
 * @author Priya Ranjan
 * @author Sumedh Patil
 * @see
 * @since Feb 2022
 *
 */

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping("/health")
	public String sayHello() {
		return "Employee Service is Up and running.";
	}

	@PostMapping("/employee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		Employee returnedEmployee = empService.saveEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnedEmployee);
	}

	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {
		return empService.getAllEmployee();
	}

	@GetMapping("/employee/{name}")
	public List<Employee> getEmployeeByName(@PathVariable String name) {
		return empService.getEmployeeByName(name);
	}

	@GetMapping("/employeeById/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		Employee returnedEmployee = empService.getEmployeeByIdAsObject(id);
		if (returnedEmployee == null) {
			throw new EmployeeNotFoundException("Employee with id " + id + " not found!!");
		}
		return returnedEmployee;
	}

	@PutMapping("/employee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException {

		if (empService.getEmployeeById(employee.getId()).isPresent()) {
			Employee updateEmployee = empService.updateEmployee(employee);
			return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
		} else {
			throw new EmployeeNotFoundException("Trainer Not Found to Update..." + employee.toString());
		}
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {

		if (empService.getEmployeeById(id).isPresent()) {
			empService.deleteEmployee(id);
			return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted " + id);
		} else {
			throw new EmployeeNotFoundException("Employee Not Found to Delete -> " + id);
		}
	}

}