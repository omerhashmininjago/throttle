# throttle

The throttle library takes care on how throttling needs to be done.

Developers, often tend to make sure, different throttling rates need to be applied to different types of requests.

This generic implementation takes care of this.

The developer needs to override the abstract class and give his implementation in providing the ThrottleRate and ThrottleKey source.

This library also takes care of the fact that no lock is left dangling in the Semaphore and hence no threads are stuck in waiting.

The developer does not need to worry about
1. when to acquire the semaphore
2. when to release the semaphore - even in case of exceptions

The developer would only need to pass the Semaphore object and the logic to be wrapped with the semaphore block wrapped as a lamba.

The implementation would return a RuntimeException in case an exception is occured
