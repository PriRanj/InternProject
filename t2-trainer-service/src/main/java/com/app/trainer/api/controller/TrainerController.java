package com.app.trainer.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.trainer.api.beans.TOC;
import com.app.trainer.api.beans.Trainer;
import com.app.trainer.api.exception.TrainerNotFoundException;
import com.app.trainer.api.proxy.TocApiProxy;
import com.app.trainer.api.service.TrainerService;

@RestController
public class TrainerController {

	@Autowired
	private TrainerService trainerService;

	@Autowired
	private TocApiProxy tocApiProxy;

	@GetMapping("/")
	public String root() {
		return "Trainer Service is up and running.";
	}

	@PostMapping("/trainer")
	public ResponseEntity<?> saveTrainer(@RequestBody Trainer trainer) {
		Trainer returnedTrainer = trainerService.saveTrainer(trainer);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnedTrainer);
	}

	@GetMapping("/trainer")
	public List<Trainer> getAllTrainers() {
		return trainerService.getAllTrainer();
	}

	@GetMapping("/trainer/{name}")
	public List<Trainer> getTrainerByName(@PathVariable String name) {
		return trainerService.getTrainerByName(name);
	}

	@GetMapping("/trainerById/{id}")
	public Trainer getTrainerById(@PathVariable("id") Integer id) throws TrainerNotFoundException {
		Trainer returnedTrainer = trainerService.getTrainerByIdAsObject(id);
		if (returnedTrainer == null) {
			throw new TrainerNotFoundException("Trainer with id " + id + " not found!!");
		}
		return returnedTrainer;
	}

	@DeleteMapping("/trainer/{id}")
	public ResponseEntity<?> deleteTrainer(@PathVariable int id) throws TrainerNotFoundException {

		if (trainerService.getTrainerById(id).isPresent()) {
			trainerService.deleteTrainer(id);
			return ResponseEntity.status(HttpStatus.OK).body("Trainer Deleted " + id);
		} else {
			throw new TrainerNotFoundException("Trainer Not Found to Delete -> " + id);
		}
	}

	@PutMapping("/trainer")
	public ResponseEntity<?> updateTrainer(@RequestBody Trainer trainer) throws TrainerNotFoundException {

		if (trainerService.getTrainerById(trainer.getId()).isPresent()) {
			Trainer updateTrainer = trainerService.updateTrainer(trainer);
			return ResponseEntity.status(HttpStatus.OK).body(updateTrainer);
		} else {
			throw new TrainerNotFoundException("Trainer Not Found to Update..." + trainer.toString());
		}
	}

	@CrossOrigin 
	@PostMapping("/trainer/toc")
	public ResponseEntity<?> saveToc(@RequestBody TOC toc) {
		return tocApiProxy.saveToc(toc);
	}

	@CrossOrigin
	@GetMapping("/trainer/toc")
	public List<TOC> getTocs(){
		return tocApiProxy.getTocs();
	}
}
