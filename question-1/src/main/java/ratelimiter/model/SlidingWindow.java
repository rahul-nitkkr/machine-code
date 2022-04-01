package ratelimiter.model;

/**
 * Represents a Window for a ciustomer , keeping track of the current request, previous requests and the current time wondow start
 */
public class SlidingWindow {

    private Integer currentRequestCount;
    private Integer previousWindowRequestCount;
    private Long currentWindowStart;
    private Integer availableCredits;

    public SlidingWindow(Integer currentRequestCount, Integer previousWindowRequestCount, Long currentWindowStart) {
        this.currentRequestCount = currentRequestCount;
        this.previousWindowRequestCount = previousWindowRequestCount;
        this.currentWindowStart = currentWindowStart;
        this.availableCredits = 0;
    }

    public Integer getCurrentRequestCount() {
        return currentRequestCount;
    }

    public Integer getAvailableCredits() {
        return availableCredits;
    }

    public Integer getPreviousWindowRequestCount() {
        return previousWindowRequestCount;
    }

    public Long getCurrentWindowStart() {
        return currentWindowStart;
    }

    public void incrementRequestCount() {
        this.currentRequestCount += 1;
    }

    public void consumeCredit() {
        this.availableCredits -= 1;
    }

    public void resetWindow(final Integer refillRequestsCount, final Integer previousWindowRequests, final Long thisWindowStart, final Integer credits) {
        this.currentRequestCount = refillRequestsCount;
        this.previousWindowRequestCount = previousWindowRequests;
        this.currentWindowStart = thisWindowStart;
        this.availableCredits = credits;
    }
}
