package ratelimiter;

import ratelimiter.model.Configuration;
import ratelimiter.model.Customer;
import ratelimiter.model.SlidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SlidingWindowRatelimiter implements RateLimiter {
    private static final Integer MAX_CREDIT_LIMIT = 1; //Configuration

    private final Integer maxAllowedRequest;
    private final Long timeWindowWidth;

    private final Map<Customer, SlidingWindow> customerRateLimitMap;
    private final Supplier<Long> timeSupplier;

    public SlidingWindowRatelimiter(Configuration configuration, Supplier<Long> timeSupplier) {
        this.maxAllowedRequest = configuration.getMaxAllowedRequests();
        this.timeWindowWidth = TimeUnit.MILLISECONDS.convert(configuration.getTimeWindow(), configuration.getTimeUnit());
        this.customerRateLimitMap = new HashMap<>();
        this.timeSupplier = timeSupplier;
    }

    @Override
    public boolean allow(Customer customer) {
        long thisRequestsTimeStamp = timeSupplier.get();
        SlidingWindow customerWindow = customerRateLimitMap.getOrDefault(customer, new SlidingWindow(0, maxAllowedRequest, thisRequestsTimeStamp));
        if (thisRequestsTimeStamp - customerWindow.getCurrentWindowStart() < timeWindowWidth) {
            //inside the window
            Long expectedCount = (((thisRequestsTimeStamp - customerWindow.getCurrentWindowStart()) / timeWindowWidth) * customerWindow.getPreviousWindowRequestCount()) + customerWindow.getCurrentRequestCount();
            if (expectedCount < maxAllowedRequest) {
                customerWindow.incrementRequestCount();
                customerRateLimitMap.put(customer, customerWindow);
                return true;
            } else if(expectedCount < maxAllowedRequest + customerWindow.getAvailableCredits()) {
                customerWindow.consumeCredit();
                customerRateLimitMap.put(customer, customerWindow);
                return true;
            }
        } else {
            //reset the window
            customerWindow.resetWindow(0,
                    customerWindow.getCurrentRequestCount(),
                    customerWindow.getCurrentWindowStart() + timeWindowWidth ,
                    Math.min((maxAllowedRequest - customerWindow.getCurrentRequestCount()) + customerWindow.getAvailableCredits() , MAX_CREDIT_LIMIT));
            customerWindow.incrementRequestCount();
            customerRateLimitMap.put(customer, customerWindow);
            return true;
        }
        return false;
    }
}
