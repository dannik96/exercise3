package cz.cvut.fel.omo.cv3.dao;

import cz.cvut.fel.omo.cv3.model.Account;
import cz.cvut.fel.omo.cv3.model.Post;
import cz.cvut.fel.omo.cv3.model.Topic;
import cz.cvut.fel.omo.cv3.model.enums.PostState;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface BlogDao {

    void createTopic(String name) throws IllegalArgumentException;

    void createPost(Set<Topic> topic, String header, String content, Account author);

    default void createPost(Topic topic, String header, String content, Account author) {
        createPost(Set.of(topic), header, content, author);
    }

    void createComment(Post post, String text);

    Collection<Topic> getTopics();

    Topic findTopic(String name);

    List<Post> getPosts();

    List<Post> getPosts(PostState state);

}
