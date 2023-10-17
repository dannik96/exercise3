package cz.cvut.fel.omo.cv3.model;

public class Comment {

    private final Post post;
    private final String text;

    public Comment(Post post, String text) {
        this.post = post;
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public String getText() {
        return text;
    }
}
