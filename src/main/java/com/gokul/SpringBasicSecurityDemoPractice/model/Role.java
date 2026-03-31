package com.gokul.SpringBasicSecurityDemoPractice.model;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permission.UPDATE, Permission.READ, Permission.WRITE, Permission.DELETE)),
    USER(Set.of(Permission.READ));

    private Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }


    Role(Set<Permission> permissions){
        this.permissions = permissions;
    }
}
