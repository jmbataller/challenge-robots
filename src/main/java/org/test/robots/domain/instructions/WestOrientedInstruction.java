package org.test.robots.domain.instructions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.test.robots.domain.Orientation;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WestOrientedInstruction implements OrientedInstruction {

    private static WestOrientedInstruction INSTANCE = new WestOrientedInstruction();

    public static WestOrientedInstruction newInstance() {
        return INSTANCE;
    }

    @Override
    public Position left(Position position) {
        return Position.with(position.getPoint(), Orientation.S);
    }

    @Override
    public Position right(Position position) {
        return Position.with(position.getPoint(), Orientation.N);
    }

    @Override
    public Position forward(Position position) {
        return Position.with(Point.of(position.getPoint().getX() - 1, position.getPoint().getY()), Orientation.W);
    }

}
