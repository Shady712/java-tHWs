package test;

import org.junit.Assert;
import org.junit.Test;
import winner.WinnerService;

import java.util.List;

public class WinnerServiceTest {

    @Test
    public void test() {
        var winnerService = new WinnerService();
        Assert.assertEquals("Petr", winnerService.findWinner(ARGS));
    }

    private static final List<String> ARGS = List.of(
            "Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"
    );
}
