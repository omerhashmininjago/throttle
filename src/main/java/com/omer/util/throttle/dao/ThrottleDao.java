package com.omer.util.throttle.dao;

import com.omer.util.throttle.domain.ThrottleInfo;

import java.util.List;

public interface ThrottleDao<T extends ThrottleInfo> {

    List<T> getThrottleRates();
}
