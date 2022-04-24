package ru.tinkoff.timer.dtos;

import java.util.Objects;

public class MethodMetricStat {

    public MethodMetricStat(String methodName) {
        this.methodName = methodName;
        this.invocationsCount = 0;
        this.minTime = Integer.MAX_VALUE;
        this.averageTime = -1;
        this.maxTime = Integer.MIN_VALUE;
    }
    /**
     * Наименование/идентификатор метода
     */
    private String methodName;
    /**
     * Кол-во вызовов метода
     */
    private Integer invocationsCount;
    /**
     * Минимальное время работы метода
     */
    private Integer minTime;
    /**
     * Среднее время работы метода
     */
    private Integer averageTime;
    /**
     * максимальное время работы метода
     */
    private Integer maxTime;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getInvocationsCount() {
        return invocationsCount;
    }

    public void setInvocationsCount(Integer invocationsCount) {
        this.invocationsCount = invocationsCount;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(Integer averageTime) {
        this.averageTime = averageTime;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public String toString() {
        return "MethodMetricStat{" +
                "methodName='" + methodName + '\'' +
                ", invocationsCount=" + invocationsCount +
                ", minTime=" + minTime +
                ", averageTime=" + averageTime +
                ", maxTime=" + maxTime +
                '}';
    }
}
