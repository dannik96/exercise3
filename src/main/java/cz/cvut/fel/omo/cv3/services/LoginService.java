package cz.cvut.fel.omo.cv3.services;

import cz.cvut.fel.omo.cv3.exceptions.UnauthorizedException;
import cz.cvut.fel.omo.cv3.model.Account;

import java.util.Optional;

public interface LoginService {

    void login(final String username, final String password) throws UnauthorizedException;

    void logout();

    Optional<Account> currentUser();

}
