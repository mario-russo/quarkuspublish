package com.br.mariorusso.adapter.in.auth;

public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
