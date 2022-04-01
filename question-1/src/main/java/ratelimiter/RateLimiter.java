package ratelimiter;

import ratelimiter.model.Customer;

/**
 * rate limiter which allows or denies a request for a customer based on the a confighuration of
 * x requests per y time interval
 */
public interface RateLimiter {

    public boolean allow(Customer customer);
}
