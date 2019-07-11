package com.martyna.catering.app.repository.auth;

import com.martyna.catering.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository  extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(String role);
}
