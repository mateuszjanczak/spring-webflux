package com.mateuszjanczak.springwebflux.exception;

public class GameNotFoundException extends Exception {
    public GameNotFoundException() {
    }

    public GameNotFoundException(String id) {
        super(String.format("Entity %s Not found", id));
    }
}
