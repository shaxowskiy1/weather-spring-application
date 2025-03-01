package ru.shaxowskiy.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException() {
        super();
    }

    public LocationNotFoundException(String message) {
        super(message);
    }
}
