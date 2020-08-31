package org.test.robots.io.in;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.robots.domain.Grid;
import org.test.robots.exceptions.ParseException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GridSizeParser implements Parser<String, Grid> {

    private static final int INDEX_OF_X = 0;
    private static final int INDEX_OF_Y = 1;
    private static final int NUM_PARAMS = 2;

    private final CoordinateParser coordinateParser;

    public Grid parse(@NonNull final String line) {
        return Optional.of(line)
                .filter(l -> !l.isEmpty())
                .map(l -> l.split(" "))
                .filter(p -> p.length == NUM_PARAMS)
                .map(params -> {
                    var sizeX = coordinateParser.parse(params[INDEX_OF_X]);
                    var sizeY = coordinateParser.parse(params[INDEX_OF_Y]);
                    return Grid.size(sizeX, sizeY);
                })
                .orElseThrow(ParseException::newGridSizeException);
    }
}
