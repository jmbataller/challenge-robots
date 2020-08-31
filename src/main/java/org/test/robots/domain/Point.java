package org.test.robots.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode
@Getter
public class Point {

    private final int x;
    private final int y;

    @Override
    public String toString() {
        return x + " " + y;
    }
}
