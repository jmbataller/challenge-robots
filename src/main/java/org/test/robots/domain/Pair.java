package org.test.robots.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode
@Getter
public class Pair<T, U> {

    private final T first;
    private final U second;
}
