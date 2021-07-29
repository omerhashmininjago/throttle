package com.omer.util.throttle;

import com.omer.util.throttle.dao.ThrottleDao;
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

    private final ThrottleDao<T> throttleDao;

    protected ThrottleService(ThrottleDao<T> throttleDao) {
        this.throttleDao = throttleDao;
    }

    /**
     * The child class would need to invoke this method
     * from a method annotated with @PostConstruct
     */
    public void init() {
        throttleLimiter = new ConcurrentHashMap<>();

        throttleDao.getThrottleRates().stream()
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