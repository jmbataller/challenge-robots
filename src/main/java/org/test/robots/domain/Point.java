package org.test.robots.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "of")
public class Point {

    // TODO: max value of coordinate is 50
    private final int x;
    private final int y;
}
