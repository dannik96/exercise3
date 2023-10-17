package cz.cvut.fel.omo.cv3.view.dashboard.components;

import cz.cvut.fel.omo.cv3.model.Comment;
import cz.cvut.fel.omo.cv3.model.Post;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostComponent implements DashboardComponent {

    private final Post post;

    public PostComponent(Post post) {
        this.post = post;
    }

    @Override
    public Optional<String> getHeader() {
        return Optional.of(post.getHeader());
    }

    @Override
    public String getContent() {
        return "Author: " + post.getAuthor().getUsername() + "\n"
                + "Created: " + post.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" +
                post.getContent() + "\n----------\n" +
                getComments();
    }

    private String getComments() {
        return post.getComments().stream()
                   .map(Comment::getText)
                   .map(s -> s + "\n----")
                   .collect(Collectors.joining("\n"));
    }
}
