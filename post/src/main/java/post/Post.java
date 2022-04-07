package post;

public class Post {
    private final String text;
    private final Integer likesCount;

    public Post(String text, Integer likesCount) {
        this.text = text;
        this.likesCount = likesCount;
    }

    public String getText() {
        return text;
    }

    public Integer getLikesCount() {
        return likesCount;
    }
}
