package org.test.robots.domain.input;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.test.robots.domain.Grid;

import java.util.List;

@Builder
@RequiredArgsConstructor(staticName = "with")
public class RobotsListInput {

    private final Grid grid;
    private final List<RobotInput> robots;

}
