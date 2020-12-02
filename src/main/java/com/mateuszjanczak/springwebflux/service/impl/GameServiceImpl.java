package com.mateuszjanczak.springwebflux.service.impl;

import com.mateuszjanczak.springwebflux.exception.GameNotFoundException;
import com.mateuszjanczak.springwebflux.model.Game;
import com.mateuszjanczak.springwebflux.repository.GameRepository;
import com.mateuszjanczak.springwebflux.service.GameService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        gameRepository.deleteAll()
                .thenMany(Flux.just(new Game("Cyberpunk 2077", "Cyberpunk 2077 to rozgrywająca się w otwartym świecie przygoda, której akcja toczy się w Night City, megalopolis rządzonym przez obsesyjną pogoń za władzą, sławą i przerabianiem własnego ciała. Nazywasz się V i musisz zdobyć jedyny w swoim rodzaju implant — klucz do nieśmiertelności.", "2077")))
                .flatMap(gameRepository::save)
                .subscribe();
    }

    @Override
    public Flux<Game> getAll() {
        return gameRepository.findAll();
    }

    @Override
    public Mono<Game> getById(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException(id)));
    }

    @Override
    public Mono<Game> save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Mono<Game> update(String id, Game game) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException(id)))
                .map(item -> {
                    item.setName(game.getName());
                    item.setDescription(game.getDescription());
                    item.setReleaseDate(game.getReleaseDate());
                    return item;
                })
                .flatMap(gameRepository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException(id)))
                .then(gameRepository.deleteById(id));
    }
}
