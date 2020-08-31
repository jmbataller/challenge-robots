package org.test.robots.domain.instructions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.test.robots.domain.Orientation;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NorthOrientedInstruction implements OrientedInstruction {

    private static NorthOrientedInstruction INSTANCE = new NorthOrientedInstruction();

    public static NorthOrientedInstruction newInstance() {
        return INSTANCE;
    }

    @Override
    public Position left(Position position) {
        return Position.with(position.getPoint(), Orientation.W);
    }

    @Override
    public Position right(Position position) {
        return Position.with(position.getPoint(), Orientation.E);
    }

    @Override
    public Position forward(Position position) {
        return Position.with(Point.of(position.getPoint().getX(), position.getPoint().getY() + 1), Orientation.N);
    }

}
