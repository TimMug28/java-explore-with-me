package ru.practicum.exceptions;

public class ConflictException extends RuntimeException {  //409

    public ConflictException(String message) {
        super(message);
    }
}
