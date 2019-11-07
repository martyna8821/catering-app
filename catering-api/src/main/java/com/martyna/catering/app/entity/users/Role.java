package com.martyna.catering.app.entity.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    private UUID id;

    @Column
    @NotBlank
    private String role;

    public Role() {}

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return this.role;
    }
}