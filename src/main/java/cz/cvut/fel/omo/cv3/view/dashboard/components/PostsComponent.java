package cz.cvut.fel.omo.cv3.view.dashboard.components;

import cz.cvut.fel.omo.cv3.model.Post;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostsComponent implements DashboardComponent {

    private final List<Post> posts;

    public PostsComponent(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public Optional<String> getHeader() {
        return Optional.of("Last posts");
    }

    @Override
    public String getContent() {
        return posts.stream()
                    .map(post -> post.getHeader() + "(" + post.getAuthor().getUsername() + ") @ " + post.getCreated()
                                                                                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .collect(Collectors.joining("\n"));
    }
}
