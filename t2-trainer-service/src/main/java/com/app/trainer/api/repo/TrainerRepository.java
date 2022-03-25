package com.app.trainer.api.repo;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.trainer.api.beans.Trainer;

@Repository
public interface TrainerRepository extends MongoRepository<Trainer,Integer> {
    List<Trainer> findByFirstname(String name);

}
