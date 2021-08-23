package com.omer.util.throttle.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public final class ThrottleInfo {

    private final String throttleKey;
    private final int throttleRate;

}
