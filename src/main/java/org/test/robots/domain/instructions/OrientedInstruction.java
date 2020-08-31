package org.test.robots.domain.instructions;

import org.test.robots.domain.Position;

public interface OrientedInstruction {
    Position left(final Position position);
    Position right(final Position position);
    Position forward(final Position position);
}
