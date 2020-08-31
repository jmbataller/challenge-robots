package org.test.robots.io;

import lombok.NonNull;
import org.test.robots.exceptions.ParseException;

import java.util.Optional;

public class CoordinateParser {

    private static final int MIN_COORDINATE_VALUE = 0;
    private static final int MAX_COORDINATE_VALUE = 50;

    public static Integer parse(@NonNull final String coordinate) {
        return Optional.of(coordinate)
                .map(Integer::valueOf)
                .filter(c -> c >= MIN_COORDINATE_VALUE && c <= MAX_COORDINATE_VALUE)
                .orElseThrow(ParseException::newCoordinateLimitException);
    }
}
