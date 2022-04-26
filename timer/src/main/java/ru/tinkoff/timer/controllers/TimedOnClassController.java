package ru.tinkoff.timer.controllers;

import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.timer.annotations.Timed;

import java.util.Random;

@Timed
@RestController
public class TimedOnClassController extends AbstractController {

    public void sleep1() throws InterruptedException {
        sleep();
    }

    public void sleep2() throws InterruptedException {
        sleep();
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(times.get(new Random().nextInt(3)));
    }
}
