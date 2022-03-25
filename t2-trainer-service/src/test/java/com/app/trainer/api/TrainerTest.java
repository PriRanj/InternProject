package com.app.trainer.api;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.trainer.api.beans.Trainer;
import com.app.trainer.api.controller.TrainerController;
import com.app.trainer.api.exception.TrainerNotFoundException;
import com.app.trainer.api.repo.TrainerRepository;
import com.app.trainer.api.service.TrainerService;

/**
 * 
 * Test cases for Trainer controller methods
 * 
 * @author Sumedh Patil
 * @author Priya Ranjan
 * @author Sangita Halder
 * 
 *
 */
@SpringBootTest
public class TrainerTest {

	@Autowired
	private TrainerService service;

	@Autowired
	private TrainerController controller;

	@MockBean
	private TrainerRepository repo;

	@BeforeEach
	public void setUp() {
		Optional<Trainer> Trainer = Optional.of(new Trainer(1, "sumedh", "patil", "sumedh@gmail.com", "9923839128",
				"1234", "amravati", 444709, "maharashtra", "india"));

		List<Trainer> list = Arrays.asList(new Trainer(1, "sumedh", "patil", "sumedh@gmail.com", "9923839128", "1234",
				"amravati", 444709, "maharashtra", "india"));

		Trainer updateTrainer = new Trainer(1, "sumedh", "patil", "sumedh112@gmail.com", "9923839128", "1234", "amravati",
				444709, "maharashtra", "india");

		Optional<Trainer> emptyTrainer = Optional.empty();
		// mocked object
		when(repo.findById(1)).thenReturn(Trainer);
		when(repo.findById(11111)).thenReturn(emptyTrainer);
		when(repo.findAll()).thenReturn(list);
		when(repo.findByFirstname("sumedh")).thenReturn(list);
		when(repo.insert(Trainer.get())).thenReturn(Trainer.get());
		when(repo.save(updateTrainer)).thenReturn(updateTrainer);

	}

	@Test
	@DisplayName("When Trainer id is given return valid object")
	public void whenTrainerIdIsGivenThenReturnObjectIfExists() throws TrainerNotFoundException {

		// checking the controller method
		assertEquals(1, controller.getTrainerById(1).getId());

	}

	@Test
	@DisplayName("when Trainer id is given return valid Trainer object")
	public void whenTrainerIdIsGivenThenReturnTrainerObjectIfExist() {
		if (repo.findById(1).isPresent()) {
			assertEquals(1, service.getTrainerByIdAsObject(1).getId());
		}
	}

	@Test
	@DisplayName("when Trainer id is given return null if not found")
	public void whenTrainerIdIsGivenThenReturnNullIfNotExist() throws TrainerNotFoundException {

		// checking the controller method
		assertThrows(TrainerNotFoundException.class, () -> controller.getTrainerById(102));

	}

	@Test
	@DisplayName("get all the Trainers and list should give you an array of 1 Trainer")
	public void getAllTrainers() {
		
		// checking the controller method
		assertEquals(1, controller.getAllTrainers().size());
	}

	@Test
	@DisplayName("save the valid Trainer and acknowledge the same")
	public void saveTrainerToTheServer() {
		Trainer trainer = new Trainer(1, "sumedh", "patil", "sumedh@gmail.com", "9923839128", "1234", "amravati",
				444709, "maharashtra", "india");

		// checking the controller method
		assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(trainer), controller.saveTrainer(trainer));

	}

	@Test
	@DisplayName("check connection")
	public void checkConnection() {
		assertEquals("Trainer Service is up and running.", controller.root());
	}

	@Test
	@DisplayName("when first name is given return the list of trainer")
	public void whenFirstNameIsGivenReturnTheListOfTrainer() {

		// checking the controller method
		assertEquals("sumedh", controller.getTrainerByName("sumedh").get(0).getFirstname());
	}

	@Test
	@DisplayName("when id is given delete if exist")
	public void whenIdIsGivenDeleteIfExist() throws TrainerNotFoundException {

		// checking the controller delete method
		assertEquals(ResponseEntity.status(HttpStatus.OK).body("Trainer Deleted 1"), controller.deleteTrainer(1));

	}

	@Test
	@DisplayName("when id is given to delete then throws exception if not found")
	public void whenIdIsGivenToDeleteThenThrowsExceptionIfNotFound() {
		// checking the controller delete method
		assertThrows(TrainerNotFoundException.class, () -> controller.deleteTrainer(102));
	}

	@Test
	@DisplayName("when trainer object is given to update then if exist update trainer")
	public void whenTrainerObjectIsGivenToUpdateThenIfExistUpdateTrainer() throws TrainerNotFoundException {

		Trainer newTrainer = new Trainer(1, "sumedh", "patil", "sumedh112@gmail.com", "9923839128", "1234", "amravati",
				444709, "maharashtra", "india");
		if (repo.findById(1).isPresent()) {
			assertEquals(ResponseEntity.status(HttpStatus.OK).body(newTrainer), controller.updateTrainer(newTrainer));
		}
	}

	@Test
	@DisplayName("when trainer object is given to update then throws exception if not found")
	public void whenTrainerObjectIsGivenToUpdateThenThrowsExceptionIfNotFound() {
		Trainer newTrainer = new Trainer(2, "sumedh", "patil", "sumedh112@gmail.com", "9923839128", "1234", "amravati",
				444709, "maharashtra", "india");
		if (repo.findById(2).isEmpty()) {
			assertThrows(TrainerNotFoundException.class, () -> controller.updateTrainer(newTrainer));
		}
	}

}