package pl.martyna.catering.app.repository.auth;

import pl.martyna.catering.app.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository  extends JpaRepository<Role, UUID> {

    Optional<Role> findByRole(String role);
}
