package org.test.robots.io.in;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.test.robots.exceptions.ParseException;

import java.util.Optional;

@Component
public class CoordinateParser implements Parser<String, Integer> {

    private static final int MIN_COORDINATE_VALUE = 0;
    private static final int MAX_COORDINATE_VALUE = 50;

    public Integer parse(@NonNull final String coordinate) {
        return Optional.of(coordinate)
                .map(Integer::parseInt)
                .filter(c -> c >= MIN_COORDINATE_VALUE && c <= MAX_COORDINATE_VALUE)
                .orElseThrow(ParseException::newCoordinateLimitException);
    }
}
