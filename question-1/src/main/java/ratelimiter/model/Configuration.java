package ratelimiter.model;

import java.util.concurrent.TimeUnit;

/**
 * configuration for {@link ratelimiter.RateLimiter} to provide X requests per y time unit
 */
public class Configuration {
    private final Integer maxAllowedRequests;
    private final Integer timeWindow;
    private final TimeUnit timeUnit;

    public Configuration(Integer maxAllowedRequests, Integer timeWindow, TimeUnit timeUnit) {
        this.maxAllowedRequests = maxAllowedRequests;
        this.timeWindow = timeWindow;
        this.timeUnit = timeUnit;
    }

    public Integer getMaxAllowedRequests() {
        return maxAllowedRequests;
    }

    public Integer getTimeWindow() {
        return timeWindow;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
