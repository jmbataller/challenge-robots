package org.test.robots.domain.output;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@RequiredArgsConstructor(staticName = "with")
public class RobotsListOutput {

    private final List<RobotOutput> robots;
}
