package com.martyna.catering.app.model;

import lombok.Data;

@Data
public class Role {

    private Integer id;

    private RoleName name;

    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }
}
