package cz.cvut.fel.omo.cv3.dao;

import cz.cvut.fel.omo.cv3.model.Account;
import cz.cvut.fel.omo.cv3.model.Comment;
import cz.cvut.fel.omo.cv3.model.Post;
import cz.cvut.fel.omo.cv3.model.Topic;
import cz.cvut.fel.omo.cv3.model.enums.PostState;

import java.util.*;
import java.util.stream.Collectors;

public class BlogDaoImpl implements BlogDao {

    private final Map<String, Topic> topics;
    private final List<Post> posts;
    private final List<Comment> comments;

    public BlogDaoImpl() {
        this.topics = new TreeMap<>();
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    @Override
    public void createTopic(String name) throws IllegalArgumentException {
        if (topics.containsKey(name)) {
            throw new IllegalArgumentException("Topic with name " + name + " already exists!");
        }

        Topic topic = new Topic(name);
        topics.put(name, topic);
    }

    @Override
    public void createPost(Set<Topic> topics, String header, String content, Account author) {
        Post post = new Post(header, content, author, PostState.IN_PROGRESS);
        post.getTopics().addAll(topics);
        topics.forEach(topic -> topic.addPost(post));
        this.posts.add(post);
    }

    @Override
    public void createComment(Post post, String text) {
        Comment comment = new Comment(post, text);
        post.addComment(comment);
        this.comments.add(comment);
    }

    @Override
    public Collection<Topic> getTopics() {
        return topics.values();
    }

    @Override
    public Topic findTopic(String name) {
        return topics.get(name);
    }

    @Override
    public List<Post> getPosts() {
        return this.posts;
    }

    @Override
    public List<Post> getPosts(PostState state) {
        return posts.stream()
                    .filter(post -> post.getState() == state)
                    .collect(Collectors.toList());
    }
}
