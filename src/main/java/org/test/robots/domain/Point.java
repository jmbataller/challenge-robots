package org.test.robots.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class Point {

    // TODO: max value of coordinate is 50
    private final int x;
    private final int y;
}
