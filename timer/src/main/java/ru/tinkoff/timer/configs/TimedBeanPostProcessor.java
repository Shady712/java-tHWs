package ru.tinkoff.timer.configs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.tinkoff.timer.annotations.Timed;

public class TimedBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Timed.class)) {

        } else {

        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
