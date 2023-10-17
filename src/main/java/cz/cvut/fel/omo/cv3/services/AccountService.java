package cz.cvut.fel.omo.cv3.services;

import cz.cvut.fel.omo.cv3.exceptions.NotFoundException;
import cz.cvut.fel.omo.cv3.model.Account;

public interface AccountService {

    Account findAccount(String username, String password) throws NotFoundException;

}
