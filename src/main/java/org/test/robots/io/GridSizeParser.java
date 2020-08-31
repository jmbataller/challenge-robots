package org.test.robots.io;

import lombok.NonNull;
import org.test.robots.domain.Grid;
import org.test.robots.exceptions.ParseException;

import java.util.Optional;

public class GridSizeParser {

    private static final int INDEX_OF_X = 0;
    private static final int INDEX_OF_Y = 1;

    public static Grid parse(@NonNull final String line) {
        return Optional.of(line)
                .filter(l -> !l.isEmpty())
                .map(l -> {
                    var params = line.split(" ");
                    var sizeX = CoordinateParser.parse(params[INDEX_OF_X]);
                    var sizeY = CoordinateParser.parse(params[INDEX_OF_Y]);
                    return Grid.size(sizeX, sizeY);
                })
                .orElseThrow(ParseException::newGridSizeException);
    }
}
