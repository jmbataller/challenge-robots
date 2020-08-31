package org.test.robots.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "with")
public class Position {

    private final Point point;
    private final Orientation orientation;

}
