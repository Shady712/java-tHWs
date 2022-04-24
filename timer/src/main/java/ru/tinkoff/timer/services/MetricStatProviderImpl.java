package ru.tinkoff.timer.services;

import org.springframework.stereotype.Service;
import ru.tinkoff.timer.dtos.MethodMetricStat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MetricStatProviderImpl {
    private final Map<String, MethodMetricStat> stats = new ConcurrentHashMap<>();

//    @Override
//    public List<MethodMetricStat> getTotalStatForPeriod(LocalDateTime from, LocalDateTime to) {
//        return null;
//    }
//
//    @Override
//    public MethodMetricStat getTotalStatByMethodForPeriod(String method, LocalDateTime from, LocalDateTime to) {
//        return null;
//    }

    public List<MethodMetricStat> getStats() {
        return stats.values().stream().toList();
    }

    public MethodMetricStat findStatByName(String methodName) {
        return stats.getOrDefault(methodName, new MethodMetricStat(methodName));
    }

    public void setStatWithName(String methodName, MethodMetricStat stat) {
        stats.putIfAbsent(methodName, stat);
    }
}
