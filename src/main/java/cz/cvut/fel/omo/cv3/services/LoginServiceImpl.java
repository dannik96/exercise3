package cz.cvut.fel.omo.cv3.services;

import cz.cvut.fel.omo.cv3.exceptions.NotFoundException;
import cz.cvut.fel.omo.cv3.exceptions.UnauthorizedException;
import cz.cvut.fel.omo.cv3.model.Account;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServiceImpl implements LoginService {

    private static final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

    private final AccountService accountService;

    private Account loggedUser;

    public LoginServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void login(String username, String password) throws UnauthorizedException {
        try {
            loggedUser = accountService.findAccount(username, password);
            logger.log(Level.INFO, "User logged in: " + loggedUser.getUsername());
        } catch (NotFoundException e) {
            throw new UnauthorizedException("User with credentials not found!", e);
        }
    }

    @Override
    public void logout() {
        logger.log(Level.INFO, "Logging out user: " + loggedUser.getUsername());
        this.loggedUser = null;
    }

    @Override
    public Optional<Account> currentUser() {
        return Optional.ofNullable(this.loggedUser);
    }
}
