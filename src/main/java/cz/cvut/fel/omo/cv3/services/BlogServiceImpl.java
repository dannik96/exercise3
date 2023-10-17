package cz.cvut.fel.omo.cv3.services;

import cz.cvut.fel.omo.cv3.dao.BlogDao;
import cz.cvut.fel.omo.cv3.exceptions.NotEnoughPermissionException;
import cz.cvut.fel.omo.cv3.model.Account;
import cz.cvut.fel.omo.cv3.model.Post;
import cz.cvut.fel.omo.cv3.model.Topic;
import cz.cvut.fel.omo.cv3.model.enums.AccountType;
import cz.cvut.fel.omo.cv3.model.enums.Permission;
import cz.cvut.fel.omo.cv3.model.enums.PostState;

import java.util.*;
import java.util.stream.Collectors;

public class BlogServiceImpl implements BlogService {

    private final BlogDao blogDao;
    private final PermissionService permissionService;

    public BlogServiceImpl(BlogDao blogDao, PermissionService permissionService) {
        this.blogDao = blogDao;
        this.permissionService = permissionService;
    }

    @Override
    public void createTopic(String name) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(Permission.CREATE_TOPIC);
        this.blogDao.createTopic(name);
    }

    @Override
    public void createPost(Set<Topic> topics, String header, String content, Account author) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(Permission.WRITE_POST);
        this.blogDao.createPost(topics, header, content, author);
    }

    @Override
    public void createComment(Post post, String text) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(Permission.WRITE_COMMENT);
        this.blogDao.createComment(post, text);
    }

    @Override
    public Topic findTopic(String name) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(Permission.READ_TOPIC);
        return this.blogDao.findTopic(name);
    }

    @Override
    public Set<Topic> getTopics() throws NotEnoughPermissionException {
        this.permissionService.checkPermission(Permission.READ_TOPIC);
        return new LinkedHashSet<>(this.blogDao.getTopics());
    }

    @Override
    public List<Post> getPosts(PostState state) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(AccountType.ADMIN);
        return this.blogDao.getPosts(state);
    }

    @Override
    public List<Post> getPosts(Account author, PostState state) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(AccountType.ADMIN);
        return this.blogDao.getPosts().stream()
                           .filter(post -> post.getAuthor().equals(author) && post.getState() == state)
                           .collect(Collectors.toList());
    }

    @Override
    public Optional<Post> getLastPost(Account author) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(AccountType.ADMIN);
        return this.blogDao.getPosts().stream()
                           .filter(post -> post.getAuthor().equals(author))
                           .max(Comparator.comparing(Post::getCreated));
    }

    @Override
    public void setPostState(Post post, PostState state) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(AccountType.ADMIN);
        post.setState(state);
    }

    @Override
    public List<Post> getPostsToRead(int count) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(Permission.READ_POST);
        return this.blogDao.getPosts(PostState.PUBLISHED).stream()
                           .sorted(Comparator.comparing(Post::getCreated))
                           .limit(count)
                           .collect(Collectors.toList());
    }

    @Override
    public List<Post> getPostsToRead(Account author, int count) throws NotEnoughPermissionException {
        this.permissionService.checkPermission(Permission.READ_POST);
        return this.blogDao.getPosts(PostState.PUBLISHED).stream()
                           .filter(post -> post.getAuthor().equals(author))
                           .limit(count)
                           .collect(Collectors.toList());
    }
}
