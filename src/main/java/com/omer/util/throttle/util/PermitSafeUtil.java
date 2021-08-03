package com.omer.util.throttle.util;

import com.omer.util.throttle.util.function.Execute;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Semaphore;

/**
 * This utility makes sure that the developer
 * does not need to take care of releasing the semaphore
 * in any type of scenario that occurs
 */
public final class PermitSafeUtil {

    private PermitSafeUtil() {}

    public static Optional<Object> execute(Semaphore semaphore, Execute execute) {

        try {
            if (!Objects.isNull(semaphore)) {
                semaphore.acquire();
            }
            return Optional.of(execute.trigger());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (!Objects.isNull(semaphore)) {
                semaphore.release();
            }
        }
    }

}
