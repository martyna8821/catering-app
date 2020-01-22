package pl.martyna.catering.app.service.users;

import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.dto.auth.RegisterRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    User findByUsername(String username);
    User findByEmail(String email);
    User findById(UUID id);
    List<User> findAll();
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    void resetPassword(String newPassword, UUID userId);
    User save(User user);
    void deleteByEmail(String email);
    User update(User user);
}
