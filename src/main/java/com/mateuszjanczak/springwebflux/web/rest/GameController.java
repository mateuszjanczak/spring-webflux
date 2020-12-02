package com.mateuszjanczak.springwebflux.web.rest;

import com.mateuszjanczak.springwebflux.model.Game;
import com.mateuszjanczak.springwebflux.service.GameService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public Flux<Game> getAll() {
        return gameService.getAll();
    }

    @GetMapping("{id}")
    public Mono<Game> getById(@PathVariable("id") String id) {
        return gameService.getById(id);
    }

    @PostMapping
    public Mono<Game> save(@RequestBody Game game) {
        return gameService.save(game);
    }

    @PutMapping("{id}")
    public Mono<Game> updateById(@PathVariable("id") String id, @RequestBody Game game) {
        return gameService.update(id, game);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        return gameService.delete(id);
    }
}
