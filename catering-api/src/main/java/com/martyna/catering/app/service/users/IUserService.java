package com.martyna.catering.app.service.users;

import com.martyna.catering.app.entity.users.User;
import com.martyna.catering.app.security.dto.RegisterRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    List<User> findAll();
    Boolean existsUserByUsernameOrEmail(String username, String email);
    boolean existsUserByEmail(String email);
    void delete(UUID id);
    void updateUser(String firstName, String lastName, String userName, String email, UUID id);
    void resetPassword(String newPassword, UUID userId);
    void updateRole(UUID roleId, UUID userId);
    User save(RegisterRequest registerRequest);
    Optional<Boolean> validateOldPassword(String username, String oldPassword);
    void deleteByEmail(String email);
}
