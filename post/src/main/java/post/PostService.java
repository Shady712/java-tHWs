package post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class PostService {
    public static List<Post> getTop10(List<Post> posts) {
        PriorityQueue<Post> queue = new PriorityQueue<>(Comparator.comparing(Post::getLikesCount).reversed());
        queue.addAll(posts);
        List<Post> res = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> res.add(queue.poll()));
        return res;
    }
}
