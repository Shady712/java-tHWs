package winner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinnerService {

    public String findWinner(List<String> scores) {
        String winner = null;
        Map<String, Integer> points = new HashMap<>();
        int max = 0;
        for (String score : scores) {
            String[] split = score.split(" ");
            int pts = points.getOrDefault(split[0], 0) + Integer.parseInt(split[1]);
            points.put(split[0], pts);
            if (pts > max) {
                max = pts;
                winner = split[0];
            }
        }
        System.out.println(winner);
        return winner;
    }
}
