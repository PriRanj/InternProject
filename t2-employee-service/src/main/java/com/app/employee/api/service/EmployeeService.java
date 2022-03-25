package com.app.employee.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.employee.api.beans.Employee;
import com.app.employee.api.repo.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public Employee saveEmployee(Employee employee) {
	return empRepo.insert(employee);
	}

	public Optional<Employee> getEmployeeById(Integer id) {
		return empRepo.findById(id); 
	}

	public  Employee getEmployeeByIdAsObject(Integer id) {
		Optional<Employee> byId = empRepo.findById(id);
		return byId.isPresent()? byId.get(): null; 
	}
	
	
	public List<Employee> getAllEmployee() {
		return empRepo.findAll(); 
	}
	public Employee updateEmployee(Employee employee) {
		return empRepo.save(employee); 
	}
	
	public List<Employee> getEmployeeByName(String name){
		return empRepo.findByFirstName(name); 
	}
	
	
	
	public void deleteEmployee(Integer Id) {
		empRepo.deleteById(Id);
	}

}
