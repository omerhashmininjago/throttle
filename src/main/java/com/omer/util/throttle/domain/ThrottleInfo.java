package com.omer.util.throttle.domain;

import java.util.Objects;

public final class ThrottleInfo {

    protected final String throttleKey;
    protected final int throttleRate;

    public ThrottleInfo(String throttleKey, int throttleRate) {
        this.throttleKey = throttleKey;
        this.throttleRate = throttleRate;
    }

    public int getThrottleRate() {
        return throttleRate;
    }

    public String getThrottleKey() {
        return throttleKey;
    }

    @Override
    public String toString() {
        return "ThrottleInfo{" +
                "throttleKey='" + throttleKey + '\'' +
                ", throttleRate=" + throttleRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThrottleInfo that = (ThrottleInfo) o;
        return throttleRate == that.throttleRate && throttleKey.equals(that.throttleKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(throttleKey, throttleRate);
    }
}
