package ru.tinkoff.timer.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.tinkoff.timer.annotations.Timed;
import ru.tinkoff.timer.services.MetricStatProviderImpl;

import java.time.Instant;

@Aspect
@Component
public class TimedAspect {
    private final MetricStatProviderImpl metricStatProvider;

    public TimedAspect(MetricStatProviderImpl metricStatProvider) {
        this.metricStatProvider = metricStatProvider;
    }

    @Pointcut("@target(timed)")
    public void targetTimedAnnotation(Timed timed) {
    }

    @Around("@within(ru.tinkoff.timer.annotations.Timed) || @annotation(ru.tinkoff.timer.annotations.Timed)")
    public Object interceptTimedMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final var methodName = proceedingJoinPoint.getSignature().getName();
        final var stat = metricStatProvider.findStatByName(methodName);
        final var startTime = Instant.now().toEpochMilli();
        final var res = proceedingJoinPoint.proceed();
        final var time = (int) (Instant.now().toEpochMilli() - startTime);
        stat.setInvocationsCount(stat.getInvocationsCount() + 1);
        stat.setMinTime(Integer.min(stat.getMinTime(), time));
        stat.setMaxTime(Integer.max(stat.getMaxTime(), time));
        stat.setAverageTime((stat.getAverageTime() * (stat.getInvocationsCount() - 1) + time) / stat.getInvocationsCount());
        metricStatProvider.setStatWithName(methodName, stat);
        return res;
    }
}
