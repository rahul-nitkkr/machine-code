import java.util.function.Supplier;

public class MockTimeSupplier implements Supplier<Long> {
    private Long currentTime;

    public MockTimeSupplier() {
        currentTime = 0L;
    }

    @Override
    public Long get() {
        currentTime += 1L;
        return currentTime;
    }
}
