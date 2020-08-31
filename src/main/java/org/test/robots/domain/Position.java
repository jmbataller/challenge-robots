package org.test.robots.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "with")
@EqualsAndHashCode
public class Position {

    private final Point point;
    private final Orientation orientation;

}
