package com.mateuszjanczak.springwebflux.service;

import com.mateuszjanczak.springwebflux.model.Game;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {
    Flux<Game> getAll();
    Mono<Game> getById(String id);
    Mono<Game> save(Game game);
    Mono<Game> update(String id, Game game);
    Mono<Void> delete(String id);
}
