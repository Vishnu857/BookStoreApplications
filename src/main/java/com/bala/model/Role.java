package com.bala.model;

import java.util.Arrays;
import java.util.List;

public enum Role {
    CUSTOMER(Arrays.asList(Permissions.READ_ALL_BOOK,Permissions.READ_ONE_BOOK,Permissions.CART)),
    ADMIN(Arrays.asList(Permissions.READ_ALL_BOOK,Permissions.SAVE_ONE_BOOK,Permissions.READ_ONE_BOOK,Permissions.DELETE_ONE_BOOK,Permissions.UPDATE_ONE_BOOK));

    private List<Permissions> permissions;

    Role(List<Permissions> permissions){
        this.permissions=permissions;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}

