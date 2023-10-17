package cz.cvut.fel.omo.cv3.services;

import cz.cvut.fel.omo.cv3.exceptions.NotEnoughPermissionException;
import cz.cvut.fel.omo.cv3.model.Account;
import cz.cvut.fel.omo.cv3.model.Post;
import cz.cvut.fel.omo.cv3.model.Topic;
import cz.cvut.fel.omo.cv3.model.enums.PostState;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BlogService {

    void createTopic(String name) throws NotEnoughPermissionException;

    void createPost(Set<Topic> topic, String header, String content, Account author) throws NotEnoughPermissionException;

    default void createPost(Topic topic, String header, String content, Account author) throws NotEnoughPermissionException {
        createPost(Set.of(topic), header, content, author);
    }

    void createComment(Post post, String text) throws NotEnoughPermissionException;

    Topic findTopic(String name) throws NotEnoughPermissionException;

    Set<Topic> getTopics() throws NotEnoughPermissionException;

    List<Post> getPosts(PostState state) throws NotEnoughPermissionException;

    List<Post> getPosts(Account author, PostState state) throws NotEnoughPermissionException;

    Optional<Post> getLastPost(Account author) throws NotEnoughPermissionException;

    void setPostState(Post post, PostState state) throws NotEnoughPermissionException;

    default void setPostState(List<Post> posts, PostState state) throws NotEnoughPermissionException {
        for (Post post : posts) {
            setPostState(post, state);
        }
    }

    default void publishPosts(List<Post> posts) throws NotEnoughPermissionException {
        this.setPostState(posts, PostState.PUBLISHED);
    }

    List<Post> getPostsToRead(int count) throws NotEnoughPermissionException;

    List<Post> getPostsToRead(Account author, int count) throws NotEnoughPermissionException;

}
