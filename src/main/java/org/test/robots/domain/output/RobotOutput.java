package org.test.robots.domain.output;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.test.robots.domain.Position;

@Builder
@RequiredArgsConstructor(staticName = "with")
@Getter
@EqualsAndHashCode
public class RobotOutput {

    private final Position position;
    private final boolean lost;

    @Override
    public String toString() {
        return lost ? position.toString().concat(" LOST") : position.toString();
    }
}
