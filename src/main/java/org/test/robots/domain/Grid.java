package org.test.robots.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "size")
@EqualsAndHashCode
public class Grid {

    private final int sizeX;
    private final int sizeY;

    public boolean isLost(final Point point) {
        return point.getX() < 0 || point.getY() < 0 || point.getX() > sizeX || point.getY() > sizeY;
    }

}
