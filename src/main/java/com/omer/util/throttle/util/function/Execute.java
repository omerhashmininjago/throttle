package com.omer.util.throttle.util.function;

@FunctionalInterface
public interface Execute<R> {

    R trigger() throws Exception;
}
