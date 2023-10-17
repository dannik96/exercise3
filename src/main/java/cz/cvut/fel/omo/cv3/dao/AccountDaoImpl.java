package cz.cvut.fel.omo.cv3.dao;

import cz.cvut.fel.omo.cv3.model.Account;
import cz.cvut.fel.omo.cv3.model.enums.AccountType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountDaoImpl implements AccountDao {

    private static final String USERS_FILE = "users.login";

    private final Map<String, Account> users;

    public AccountDaoImpl() {
        try {
            this.users = Files.readAllLines(Path.of(Objects.requireNonNull(getClass().getClassLoader()
                                                                                     .getResource(USERS_FILE)).toURI()))
                              .stream()
                              .map(line -> line.split(":"))
                              .map(line -> new Account(line[0], line[1], AccountType.valueOf(line[2])))
                              .collect(Collectors.toMap(Account::getUsername, account -> account));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Cannot load users!", e);
        }
    }

    @Override
    public Optional<Account> findAccount(String username, String password) {
        Account account = users.get(username);

        if (account != null && account.getPassword().equals(password)) {
            return Optional.of(account);
        }

        return Optional.empty();
    }
}
