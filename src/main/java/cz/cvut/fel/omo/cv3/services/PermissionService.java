package cz.cvut.fel.omo.cv3.services;

import cz.cvut.fel.omo.cv3.exceptions.NotEnoughPermissionException;
import cz.cvut.fel.omo.cv3.model.enums.AccountType;
import cz.cvut.fel.omo.cv3.model.enums.Permission;

import java.util.Set;

public interface PermissionService {

    void checkPermission(AccountType accountType) throws NotEnoughPermissionException;

    void checkPermission(Permission permission) throws NotEnoughPermissionException;

    void checkPermission(Set<Permission> permissions) throws NotEnoughPermissionException;

}
