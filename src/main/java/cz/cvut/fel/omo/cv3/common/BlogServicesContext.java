package cz.cvut.fel.omo.cv3.common;

import cz.cvut.fel.omo.cv3.dao.AccountDao;
import cz.cvut.fel.omo.cv3.dao.BlogDao;
import cz.cvut.fel.omo.cv3.services.*;


public class BlogServicesContext {

    private final LoginService loginService;
    private final AccountService accountService;
    private final PermissionService permissionService;
    private final BlogService blogService;

    public BlogServicesContext(BlogDao blogDao, AccountDao accountDao) {
        this.accountService = new AccountServiceImpl(accountDao);
        this.loginService = new LoginServiceImpl(accountService);
        this.permissionService = new PermissionServiceImpl(loginService);
        this.blogService = new BlogServiceImpl(blogDao, permissionService);
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public BlogService getBlogService() {
        return blogService;
    }
}
