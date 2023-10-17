package cz.cvut.fel.omo.cv3.model;

import cz.cvut.fel.omo.cv3.model.enums.PostState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post {

    private String header;
    private String content;
    private final Account author;
    private PostState state;
    private final LocalDateTime created;
    private final Set<Topic> topics;
    private final List<Comment> comments;

    public Post(String header, String content, Account author, PostState state) {
        this.header = header;
        this.content = content;
        this.author = author;
        this.state = state;
        this.created = LocalDateTime.now();
        this.topics = new HashSet<>();
        this.comments = new ArrayList<>();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getAuthor() {
        return author;
    }

    public PostState getState() {
        return state;
    }

    public void setState(PostState state) {
        this.state = state;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
