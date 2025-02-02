package org.test.robots.exceptions;

public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super(message);
    }

    public static ParseException newGridSizeException() {
        return new ParseException("Invalid grid size");
    }

    public static ParseException newInvalidPositionException() {
        return new ParseException("Invalid position");
    }

    public static ParseException newCoordinateLimitException() {
        return new ParseException("coordinate needs to be between 0 and 50");
    }

    public static ParseException newInstructionsLimitException() {
        return new ParseException("instructions have to be less than 100");
    }
}
