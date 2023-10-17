package cz.cvut.fel.omo.cv3.services;

import cz.cvut.fel.omo.cv3.common.TextUtils;
import cz.cvut.fel.omo.cv3.dao.AccountDao;
import cz.cvut.fel.omo.cv3.exceptions.NotFoundException;
import cz.cvut.fel.omo.cv3.model.Account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService {

    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    private static final String SALT = "SALT";

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccount(String username, String password) throws NotFoundException {
        return accountDao.findAccount(username, encryptPassword(password))
                         .orElseThrow(() -> new NotFoundException("Account with username " + username + " not found."));
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(SALT.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return TextUtils.byteArrayToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, null, e);
            return null;
        }
    }
}
