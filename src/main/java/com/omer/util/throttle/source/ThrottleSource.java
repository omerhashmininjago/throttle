package com.omer.util.throttle.source;

import com.omer.util.throttle.domain.ThrottleInfo;

import java.util.List;

/**
 * <p>
 * This class will be the source to fetch the
 * list of throttle Rates based on which the
 * throttling would be applied to for the correspondng
 * different requests
 * </p>
 *
 * @param <T>
 */
public interface ThrottleSource<T extends ThrottleInfo> {

    List<T> getThrottleRates();
}
