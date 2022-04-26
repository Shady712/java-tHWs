package ru.tinkoff.timer.controllers;

import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.timer.annotations.Timed;

import java.util.List;
import java.util.Random;

@RestController
public class TimedOnMethodController extends AbstractController {

    @Timed
    public void sleep() throws InterruptedException {
        Thread.sleep(times.get(new Random().nextInt(3)));
    }
}
