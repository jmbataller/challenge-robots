package org.test.robots.domain.instructions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.test.robots.domain.Orientation;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SouthOrientedInstruction implements OrientedInstruction {

    private static SouthOrientedInstruction INSTANCE = new SouthOrientedInstruction();

    public static SouthOrientedInstruction newInstance() {
        return INSTANCE;
    }

    @Override
    public Position left(Position position) {
        return Position.with(position.getPoint(), Orientation.E);
    }

    @Override
    public Position right(Position position) {
        return Position.with(position.getPoint(), Orientation.W);
    }

    @Override
    public Position forward(Position position) {
        return Position.with(Point.of(position.getPoint().getX(), position.getPoint().getY() - 1), Orientation.S);
    }

}
