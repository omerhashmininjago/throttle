package com.omer.util.throttle;

import com.omer.util.throttle.source.ThrottleSource;
import com.omer.util.throttle.domain.ThrottleInfo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.function.Predicate;

/**
 * <p>
 *     A generic service which would be extended
 *     by services which need to throttle requests
 * </p>
 * @param <T>
 */
public abstract class ThrottleService<T extends ThrottleInfo> {

    private ConcurrentHashMap<String, Semaphore> throttleLimiter;
    private final Predicate<T> IS_THROTTLING_ENABLED = bean -> bean.getThrottleRate() > 0;

    private final ThrottleSource<T> throttleSource;

    protected ThrottleService(ThrottleSource<T> throttleSource) {
        this.throttleSource = throttleSource;
    }

    /**
     * The child class would need to invoke this method
     * from a method annotated with @PostConstruct
     */
    public void init() {
        throttleLimiter = new ConcurrentHashMap<>();

        throttleSource.getThrottleRates().stream()
                .filter(IS_THROTTLING_ENABLED)
                .forEach(bean ->
                        this.throttleLimiter
                                .put(bean.getThrottleKey(), new Semaphore(bean.getThrottleRate(), true))
                );
    }

    public Semaphore getThrottleLimiter(String throttleKey) {
        return this.throttleLimiter.get(throttleKey);
    }

}