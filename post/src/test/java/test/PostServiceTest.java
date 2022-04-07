package test;

import org.junit.Test;
import post.Post;
import post.PostService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PostServiceTest {

    @Test
    public void test() {
        List<Post> posts = new ArrayList<>();
        IntStream.range(0, 1000000).forEach(i -> posts.add(new Post("text", new Random(i).nextInt())));
        var res = PostService.getTop10(posts);
        posts.sort(Comparator.comparing(Post::getLikesCount).reversed());
        var top10 = posts.subList(0, 10);
        res.forEach(post -> {
            assert top10.contains(post);
        });
    }
}
