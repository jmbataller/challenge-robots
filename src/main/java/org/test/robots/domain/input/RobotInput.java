package org.test.robots.domain.input;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.test.robots.domain.Instruction;
import org.test.robots.domain.Position;

import java.util.List;

@Builder
@RequiredArgsConstructor(staticName = "with")
@EqualsAndHashCode
@Getter
public class RobotInput {

    private final Position position;
    private final List<Instruction> instructions;

}
