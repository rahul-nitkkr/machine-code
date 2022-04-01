import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ratelimiter.RateLimiter;
import ratelimiter.SlidingWindowRatelimiter;
import ratelimiter.model.Configuration;
import ratelimiter.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSlidignWindowRatelimit {

    @Test
    public void testSlidingWindow_SingleCustomer() {
        Customer cust = new Customer("cust");
        RateLimiter rateLimiter = new SlidingWindowRatelimiter(new Configuration(2 , 5 , TimeUnit.MILLISECONDS) , new MockTimeSupplier());

        List<Boolean> responses = new ArrayList<>();
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));

        System.out.println(responses);
        Assertions.assertEquals(4 , responses.stream().filter(resp -> resp).count());
    }

    @Test
    public void testSlidingWindow_Credit() {
        Customer cust = new Customer("cust");
        RateLimiter rateLimiter = new SlidingWindowRatelimiter(new Configuration(2 , 1 , TimeUnit.MILLISECONDS) , new MockTimeSupplier());

        List<Boolean> responses = new ArrayList<>();
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));
        responses.add(rateLimiter.allow(cust));

        System.out.println(responses);
        Assertions.assertEquals(7 , responses.stream().filter(resp -> resp).count());
    }

}
