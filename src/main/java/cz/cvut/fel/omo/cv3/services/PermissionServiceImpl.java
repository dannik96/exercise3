package cz.cvut.fel.omo.cv3.services;

import cz.cvut.fel.omo.cv3.exceptions.NotEnoughPermissionException;
import cz.cvut.fel.omo.cv3.model.enums.AccountType;
import cz.cvut.fel.omo.cv3.model.enums.Permission;

import java.util.Set;

public class PermissionServiceImpl implements PermissionService {

    private final LoginService loginService;

    public PermissionServiceImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void checkPermission(AccountType accountType) throws NotEnoughPermissionException {
        loginService.currentUser()
                    .filter(account -> !account.isBlocked())
                    .filter(account -> accountType == account.getAccountType())
                    .orElseThrow(() -> new NotEnoughPermissionException("Account must be of type " + accountType));
    }

    @Override
    public void checkPermission(Permission permission) throws NotEnoughPermissionException {
        loginService.currentUser()
                    .filter(account -> !account.isBlocked())
                    .filter(account -> account.getAccountType().hasPermission(permission))
                    .orElseThrow(() -> new NotEnoughPermissionException("Account must have permission " + permission));
    }

    @Override
    public void checkPermission(Set<Permission> permissions) throws NotEnoughPermissionException {
        loginService.currentUser()
                    .filter(account -> !account.isBlocked())
                    .filter(account -> account.getAccountType().getPermissions().containsAll(permissions))
                    .orElseThrow(() -> new NotEnoughPermissionException("Account must have permissions " + permissions));
    }
}
