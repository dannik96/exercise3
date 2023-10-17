package cz.cvut.fel.omo.cv3.model;

import cz.cvut.fel.omo.cv3.model.enums.AccountType;

public class Account {

    private final String username;
    private final String password;
    private AccountType accountType;
    private boolean blocked = false;

    public Account(String username, String password, AccountType accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String simpleStringSerialization() {
        return username + ":" + password + ":" + accountType.name();
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
