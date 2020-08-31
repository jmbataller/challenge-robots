package org.test.robots.domain.output;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@RequiredArgsConstructor(staticName = "with")
@Getter
public class RobotsListOutput {

    private final List<RobotOutput> robots;
}
