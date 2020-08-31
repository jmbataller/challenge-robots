package org.test.robots.io.in;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.robots.domain.Orientation;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;
import org.test.robots.exceptions.ParseException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PositionParser implements Parser<String, Position> {

    private static final int INDEX_OF_X = 0;
    private static final int INDEX_OF_Y = 1;
    private static final int INDEX_OF_ORIENTATION = 2;
    private static final int NUM_PARAMS = 3;

    private final CoordinateParser coordinateParser;

    public Position parse(@NonNull final String line) {
        return Optional.of(line)
                .filter(l -> !l.isEmpty())
                .map(l -> l.split(" "))
                .filter(l -> l.length == NUM_PARAMS)
                .map(params -> {
                    var x = coordinateParser.parse(params[INDEX_OF_X]);
                    var y = coordinateParser.parse(params[INDEX_OF_Y]);
                    var point = Point.of(x, y);
                    var orientation = Orientation.valueOf(params[INDEX_OF_ORIENTATION]);
                    return Position.with(point, orientation);
                })
                .orElseThrow(ParseException::newInvalidPositionException);
    }
}
