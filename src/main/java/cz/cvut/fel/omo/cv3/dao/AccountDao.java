package cz.cvut.fel.omo.cv3.dao;

import cz.cvut.fel.omo.cv3.model.Account;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> findAccount(String username, String password);

}
