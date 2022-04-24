package ru.tinkoff.timer.annotations;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {
}
