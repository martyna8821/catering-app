package com.martyna.catering.app.repository.auth;

import com.martyna.catering.app.entity.User;
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
    List<User> findAll();

    @Modifying
    @Transactional
    @Query(value = "delete from users where id = ?1", nativeQuery = true)
    void delete(UUID id);

    @Modifying
    @Transactional
    @Query(value = "update users set first_name = ?1, last_name = ?2 , username = ?3, " +
            "email = ?4 where id = ?5" ,nativeQuery = true)
    void updateUser(String firstName, String lastName, String userName, String email, UUID id);

    @Modifying
    @Transactional
    @Query(value = "update users password = ?1 where id = ?2", nativeQuery = true)
    void resetPassword(String password, UUID id);

    @Modifying
    @Transactional
    @Query(value = "update users_roles set role_id = ?1 where user_id = ?2", nativeQuery = true)
    void updateRole(UUID roleId, UUID userId);

}
