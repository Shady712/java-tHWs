package ru.tinkoff.timer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.timer.controllers.BlockingController;
import ru.tinkoff.timer.services.MetricStatProviderImpl;

import java.util.stream.IntStream;

@SpringBootTest
class TimerApplicationTests {
    @Autowired private BlockingController blockingController;
    @Autowired private MetricStatProviderImpl metricStatProvider;

    @Test
    void testMetrics() {
        IntStream.range(0, 10).forEach(ignored -> {
            try {
                blockingController.sleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final var metrics = metricStatProvider.getStats();
        System.out.println("metrics size is " + metrics.size());
        metrics.forEach(System.out::println);
    }
}
