package ru.tinkoff.timer.controllers;

import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.timer.annotations.Timed;

import java.util.List;
import java.util.Random;

@RestController
public class BlockingController {

    @Timed
    public void sleep() throws InterruptedException {
        Thread.sleep(times.get(new Random().nextInt(3)));
    }

    private static final List<Integer> times = List.of(100, 200, 300);
}
