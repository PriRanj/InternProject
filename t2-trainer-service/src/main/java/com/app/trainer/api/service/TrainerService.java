package com.app.trainer.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.trainer.api.beans.Trainer;
import com.app.trainer.api.repo.TrainerRepository;

@Service
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepo;

	public Trainer saveTrainer(Trainer trainer) {
		return trainerRepo.insert(trainer);
	}
	public Optional<Trainer> getTrainerById(Integer id) {
		return trainerRepo.findById(id);
	}

	public Trainer getTrainerByIdAsObject(Integer id) {
		Optional<Trainer> byId = trainerRepo.findById(id);
		return byId.isPresent() ? byId.get() : null;
	}

	public List<Trainer> getAllTrainer() {
		return trainerRepo.findAll();
	}

	public List<Trainer> getTrainerByName(String name) {
		return trainerRepo.findByFirstname(name);
	}

	public Trainer updateTrainer(Trainer trainer) {
		return trainerRepo.save(trainer);
	}

	public void deleteTrainer(Integer Id) {
		trainerRepo.deleteById(Id);
	}

}
