package com.martyna.catering.app.service;

import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.security.dto.RegisterRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface IUserService {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    Stream<User> findAll();
    Boolean existsUserByUsernameOrEmail(String username, String email);
    boolean existsUserByEmail(String email);
    void delete(UUID id);
    void updateUser(String firstName, String lastName, String userName, String email, UUID id);
  //  String resetPassword( String id);
    void resetPassword(String newPassword, UUID userId);
    void updateRole(UUID roleId, UUID userId);
    User save(RegisterRequest registerRequest);
    Optional<Boolean> validateOldPassword(String username, String oldPassword);
}
