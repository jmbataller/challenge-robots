package org.test.robots.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "with")
@EqualsAndHashCode
@Getter
public class Position {

    private final Point point;
    private final Orientation orientation;

    @Override
    public String toString() {
        return point.toString().concat(" ").concat(orientation.getValue());
    }
}
