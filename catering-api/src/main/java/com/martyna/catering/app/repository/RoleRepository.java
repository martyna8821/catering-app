package com.martyna.catering.app.repository;

import com.martyna.catering.app.model.Role;
import com.martyna.catering.app.model.RoleName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository {

    Role findByName(RoleName roleName){
        return new Role(roleName);
    }
}
