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

}
