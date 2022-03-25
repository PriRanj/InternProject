package com.app.employee.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.employee.api.beans.Employee;
import com.app.employee.api.controller.EmployeeController;
import com.app.employee.api.exception.EmployeeNotFoundException;
import com.app.employee.api.repo.EmployeeRepository;

@SpringBootTest
public class EmployeeTest {
	
	@Autowired
	private EmployeeController controller;
	
    @MockBean
	private EmployeeRepository repo;
    
    @BeforeEach
	public void setUp() {
		Optional<Employee> employee = Optional
				.of(new Employee(101, "Ria", "sinha", "ria@gmail.com","mobile a","pass","jaipur", 30025, "Rajasthan", "India"));
		List<Employee> list = Arrays
				.asList(new Employee(101, "Ria", "sinha", "ria@gmail.com","mobile a","pass","jaipur", 30025, "Rajasthan", "India"));
		
		Employee updatedEmployee = new Employee(101, "Simran", "sinha", "ria@gmail.com","mobile b","pass","jaipur", 30025, "Rajasthan", "India");
		Optional<Employee> emptyEmployee = Optional.empty();
		// mocked object
		when(repo.findById(101)).thenReturn(employee);
		when(repo.findById(102)).thenReturn(emptyEmployee);
		when(repo.findAll()).thenReturn(list);
		when(repo.insert(employee.get())).thenReturn(employee.get());
		when(repo.findByFirstName("Ria")).thenReturn(list);
		when(repo.save(updatedEmployee)).thenReturn(updatedEmployee);

	}
    
    @Test
	@DisplayName("When employee id is given return valid object")
	public void whenEmployeeIdIsGivenThenReturnObjectIfExists() throws EmployeeNotFoundException {

		// checking the controller method
		assertEquals(101, controller.getEmployeeById(101).getId());

	}
    
    @Test
    @DisplayName("when first name is given return the list of employees")
    public void whenFirstNameIsGivenReturnTheListOfEmployees() {
    	
    	//checking the controller method
    	assertEquals("Ria", controller.getEmployeeByName("Ria").get(0).getFirstName());
    }
  
    @Test
	@DisplayName("when employee id is given return null if not found")
	public void whenEmployeeIdIsGivenThenReturnNullIfNotExist() throws EmployeeNotFoundException {

		// checking the controller method
		assertThrows(EmployeeNotFoundException.class, () -> controller.getEmployeeById(102));

	}
    @Test
	@DisplayName("get all the employee and list should give you an array of 1 employee")
	public void getAllEmployee() {
		// checking the controller method
		assertEquals(1, controller.getAllEmployees().size());
	}
    @Test
	@DisplayName("save the valid employee and acknowledge the same")
	public void saveEmployeeToTheServer() {
		Employee employee = new Employee(101, "Ria", "sinha", "ria@gmail.com","mobile a","pass","jaipur", 30025, "Rajasthan", "India");
		
		//checking the controller method
		 assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(employee ),controller.saveEmployee(employee));
		 
	}
    
    @Test
    @DisplayName("when id is given delete if exist")
    public void whenIdIsGivenDeleteIfExist() throws EmployeeNotFoundException {
    	
    	//checking the controller delete method
    	assertEquals(ResponseEntity.status(HttpStatus.OK).body("Employee Deleted 101"), controller.deleteEmployee(101));
    	
    }
    
    @Test
    @DisplayName("when id is given to delete then throws exception if not found")
    public void whenIdIsGivenToDeleteThenThrowsExceptionIfNotFound() {
    	//checking the controller delete method
    	assertThrows(EmployeeNotFoundException.class, () -> controller.deleteEmployee(102));
    }
    
    @Test
    @DisplayName("when employee object is given to update then if exist update employee")
    public void whenEmployeeObjectIsGivenToUpdateThenIfExistUpdateEmployee() throws EmployeeNotFoundException {
    	
    	Employee newEmployee = new Employee(101, "Simran", "sinha", "ria@gmail.com","mobile b","pass","jaipur", 30025, "Rajasthan", "India");
    	if(repo.findById(101).isPresent()) {
    		assertEquals(ResponseEntity.status(HttpStatus.OK).body(newEmployee), controller.updateEmployee(newEmployee));
    	}
    }
    
    @Test
    @DisplayName("when employee object is given to update then throws exception if not found")
    public void whenEmployeeObjectIsGivenToUpdateThenThrowsExceptionIfNotFound() {
    	Employee newEmployee = new Employee(102, "Simran", "sinha", "ria@gmail.com","mobile b","pass","jaipur", 30025, "Rajasthan", "India");
    	if(repo.findById(102).isEmpty()) {
    		assertThrows(EmployeeNotFoundException.class, () -> controller.updateEmployee(newEmployee));
    	}
    }
    
    
    @Test
	@DisplayName("check connection")
	public void checkConnection() {
		assertEquals("Employee Service is Up and running.", controller .sayHello()); 
	}
    

	

}
