package org.test.robots.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.test.robots.domain.instructions.*;

@AllArgsConstructor
@Getter
public enum Orientation {

    N("N", NorthOrientedInstruction.newInstance()),
    E("E", EastOrientedInstruction.newInstance()),
    S("S", SouthOrientedInstruction.newInstance()),
    W("W", WestOrientedInstruction.newInstance());

    private String value;
    private OrientedInstruction instruction;
}
