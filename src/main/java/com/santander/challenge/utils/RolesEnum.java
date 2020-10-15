package com.santander.challenge.utils;

/**
 * @author guillermovarelli
 */

public enum RolesEnum {
    ADMIN("admin","ROLE_ADMIN"),
    USER("user","ROLE_USER");

    private String role;
    private String type;

    RolesEnum(String role,String type) {
        this.role = role;
        this.type = type;
    }


    public String getRole() {
        return this.role;
    }

    public String getRoleType() {
        return this.type;
    }

}
