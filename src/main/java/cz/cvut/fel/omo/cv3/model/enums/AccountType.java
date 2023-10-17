package cz.cvut.fel.omo.cv3.model.enums;

import java.util.Set;

public enum AccountType {

    USER(Set.of(Permission.READ_POST, Permission.READ_TOPIC, Permission.READ_COMMENT, Permission.WRITE_COMMENT)),
    ADMIN(Set.of(Permission.READ_POST, Permission.WRITE_POST, Permission.READ_TOPIC, Permission.CREATE_TOPIC, Permission.READ_COMMENT, Permission.WRITE_COMMENT, Permission.CAN_BLOCK_ACCOUNTS, Permission.CREATE_ADMIN_ACCOUNT));

    private final Set<Permission> permissions;

    AccountType(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}
