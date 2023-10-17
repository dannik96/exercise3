package cz.cvut.fel.omo.cv3.view.dashboard;

import cz.cvut.fel.omo.cv3.exceptions.NotEnoughPermissionException;
import cz.cvut.fel.omo.cv3.model.Account;
import cz.cvut.fel.omo.cv3.model.Post;
import cz.cvut.fel.omo.cv3.model.Topic;
import cz.cvut.fel.omo.cv3.services.BlogService;
import cz.cvut.fel.omo.cv3.view.dashboard.components.DashboardComponent;
import cz.cvut.fel.omo.cv3.view.dashboard.components.PostComponent;
import cz.cvut.fel.omo.cv3.view.dashboard.components.PostsComponent;
import cz.cvut.fel.omo.cv3.view.dashboard.components.TopicsComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DashboardFactory {

    public static Dashboard createDefaultUserDashboard(BlogService blogService) throws NotEnoughPermissionException {
        List<Post> lastPosts = blogService.getPostsToRead(6);

        PostsComponent postsComponent = new PostsComponent(lastPosts.subList(1, lastPosts.size()));
        PostComponent postComponent = new PostComponent(lastPosts.get(0));

        return new Dashboard(List.of(postsComponent, postComponent));
    }

    public static Dashboard createDefaultAdminDashboard(BlogService blogService, Account loggedUser) throws NotEnoughPermissionException {
        List<DashboardComponent> components = new ArrayList<>();

        Set<Topic> topics = blogService.getTopics();
        TopicsComponent topicsComponent = new TopicsComponent(topics);
        components.add(topicsComponent);

        Optional<Post> lastPost = blogService.getLastPost(loggedUser);
        lastPost.map(PostComponent::new).ifPresent(components::add);

        return new Dashboard(components);
    }
}
