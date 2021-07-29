package com.omer.util.throttle.util;

import com.omer.util.throttle.util.function.Execute;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Semaphore;

public final class LockSafeUtil {

    private LockSafeUtil() {}

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
