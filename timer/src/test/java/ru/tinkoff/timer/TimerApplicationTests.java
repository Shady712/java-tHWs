package ru.tinkoff.timer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.timer.controllers.TimedOnClassController;
import ru.tinkoff.timer.controllers.TimedOnMethodController;
import ru.tinkoff.timer.dtos.MethodMetricStat;
import ru.tinkoff.timer.services.MetricStatProviderImpl;

import java.util.stream.IntStream;

@SpringBootTest
class TimerApplicationTests {
    private final MetricStatProviderImpl metricStatProvider;

    TimerApplicationTests(@Autowired MetricStatProviderImpl metricStatProvider) {
        this.metricStatProvider = metricStatProvider;
    }

    @BeforeEach
    public void clear() {
        metricStatProvider.resetMetrics();
    }

    @Test
    void testTimedOnMethod(@Autowired TimedOnMethodController controller) {
        IntStream.range(0, 10).forEach(ignored -> {
            try {
                controller.sleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final var metrics = metricStatProvider.getStats();
        assert metrics.size() == 1;
        assert metrics.get(0).getInvocationsCount() == 10;
    }

    @Test
    void testTimedOnClass(@Autowired TimedOnClassController controller) {
        IntStream.range(0, 10).forEach(ignored -> {
            try {
                controller.sleep1();
                controller.sleep2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final var metrics = metricStatProvider.getStats();
        assert metrics.size() == 2;
        metrics.forEach(stat -> Assertions.assertEquals(stat.getInvocationsCount(), 10));
    }
}
