package com.mateuszjanczak.springwebflux.repository;

import com.mateuszjanczak.springwebflux.model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {

}
