package pl.martyna.catering.app.repository.user;

import pl.martyna.catering.app.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    Boolean existsUserByUsername(String username);
    Boolean existsUserByEmail(String email);

    User saveAndFlush(User user);

    @Transactional
    void deleteByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "delete from users where user_id = ?1", nativeQuery = true)
    void delete(UUID id);

    @Modifying
    @Transactional
    @Query(value = "update users set first_name = ?1, last_name = ?2 , username = ?3, " +
            "email = ?4 where user_id = ?5" ,nativeQuery = true)
    void updateUser(String firstName, String lastName, String username, String email, UUID id);

    @Modifying
    @Transactional
    @Query(value = "update users set password = ?1 where user_id = ?2", nativeQuery = true)
    void resetPassword(String password, UUID id);

    @Modifying
    @Transactional
    @Query(value = "update users_roles set role_id = ?1 where user_id = ?2", nativeQuery = true)
    void updateRole(UUID roleId, UUID userId);

}
