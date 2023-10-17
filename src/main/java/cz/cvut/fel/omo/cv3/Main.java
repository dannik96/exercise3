package cz.cvut.fel.omo.cv3;

import cz.cvut.fel.omo.cv3.common.BlogServicesContext;
import cz.cvut.fel.omo.cv3.dao.AccountDao;
import cz.cvut.fel.omo.cv3.dao.AccountDaoImpl;
import cz.cvut.fel.omo.cv3.dao.BlogDao;
import cz.cvut.fel.omo.cv3.dao.BlogDaoImpl;
import cz.cvut.fel.omo.cv3.exceptions.NotEnoughPermissionException;
import cz.cvut.fel.omo.cv3.exceptions.UnauthorizedException;
import cz.cvut.fel.omo.cv3.model.Account;
import cz.cvut.fel.omo.cv3.model.Post;
import cz.cvut.fel.omo.cv3.model.Topic;
import cz.cvut.fel.omo.cv3.model.enums.PostState;
import cz.cvut.fel.omo.cv3.services.BlogService;
import cz.cvut.fel.omo.cv3.view.dashboard.Dashboard;
import cz.cvut.fel.omo.cv3.view.dashboard.DashboardFactory;

import java.util.List;

public class Main {

    private final static String TOPIC_1 = "Software architecture";
    private final static String TOPIC_2 = "Class diagram";

    public static void main(String[] args) throws UnauthorizedException, NotEnoughPermissionException {
        BlogDao blogDao = new BlogDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();

        // admin login
        BlogServicesContext context1 = new BlogServicesContext(blogDao, accountDao);
        BlogService blogService = context1.getBlogService();
        context1.getLoginService().login("admin", "admin");

        // create topics
        blogService.createTopic(TOPIC_1);
        blogService.createTopic(TOPIC_2);

        Account admin = context1.getLoginService().currentUser()
                                .orElseThrow(() -> new IllegalStateException("User should be logged in!"));
        Topic topic1 = blogService.findTopic(TOPIC_1);
        Topic topic2 = blogService.findTopic(TOPIC_2);

        // create posts
        blogService.createPost(topic1, "Microservices are the future?", "Only God knows ...", admin);
        blogService.createPost(topic1, "Monolithic architecture", "Not great, not terrible (sometimes) ...", admin);
        blogService.createPost(topic1, "Hexagonal architecture", "Architecture with exotic taste ...", admin);
        blogService.createPost(topic2, "Class diagram standards", "Use google and find them ...", admin);
        blogService.createPost(topic2, "Class diagram tools", "Enterprise Architect, DrawIO, ...", admin);
        blogService.createPost(topic2, "UML relations", "Associations, aggregations, compositions, ...", admin);

        // publish all posts
        List<Post> adminPosts = blogService.getPosts(admin, PostState.IN_PROGRESS);
        blogService.publishPosts(adminPosts);

        // create dashboard
        Dashboard dashboard1 = DashboardFactory.createDefaultAdminDashboard(blogService, admin);
        dashboard1.printDashboard();
        context1.getLoginService().logout();

        System.out.println("================================================================================");

        // sample login 2
        BlogServicesContext context2 = new BlogServicesContext(blogDao, accountDao);
        context2.getLoginService().login("user", "user");
        Dashboard dashboard2 = DashboardFactory.createDefaultUserDashboard(context2.getBlogService());
        dashboard2.printDashboard();
        context2.getLoginService().logout();

    }

}
