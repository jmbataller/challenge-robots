package org.test.robots.domain.input;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.test.robots.domain.Grid;

import java.util.List;

@Builder
@RequiredArgsConstructor(staticName = "with")
@EqualsAndHashCode
public class RobotsListInput {

    private final Grid grid;
    private final List<RobotInput> robots;

}
