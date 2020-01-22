package pl.martyna.catering.app.service.users.impl;

import pl.martyna.catering.app.entity.auth.Role;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.exception.UserNotFoundException;
import pl.martyna.catering.app.repository.user.IAddressRepository;
import pl.martyna.catering.app.repository.auth.IRoleRepository;
import pl.martyna.catering.app.repository.user.IUserRepository;
import pl.martyna.catering.app.dto.auth.RegisterRequest;
import pl.martyna.catering.app.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService( IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                             .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                             .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return this.userRepository.existsUserByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public void resetPassword(String newPassword, UUID userId) {
        userRepository.resetPassword(passwordEncoder.encode(newPassword), userId);
    }

    @Override
    public User update(User user){
        return this.userRepository.save(user);
    }

    @Override
    public void deleteByEmail(String email){
        this.userRepository.deleteByEmail(email);
    }

}
