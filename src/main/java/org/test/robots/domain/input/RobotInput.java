package org.test.robots.domain.input;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.test.robots.domain.Instruction;
import org.test.robots.domain.Position;

import java.util.List;

@Builder
@RequiredArgsConstructor(staticName = "with")
public class RobotInput {

    private final Position position;

    // TODO: instructions limit is 100
    private final List<Instruction> instructions;

}
