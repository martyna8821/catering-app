package com.martyna.catering.app.service;

import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.repository.auth.IUserRepository;
import com.martyna.catering.app.security.dto.RegisterRequest;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Stream<User> findAll() {
        return userRepository.findAll().stream();
    }

    @Override
    public Boolean existsUserByUsernameOrEmail(String username, String email) {
        return userRepository.existsUserByUsernameOrEmail(username, email);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }

    @Override
    public void updateUser(String firstName, String lastName, String userName, String email, UUID id) {
        userRepository.updateUser(firstName, lastName, userName, email, id );
    }

    @Override
    public void resetPassword(String password, UUID id) {
        userRepository.resetPassword(password, id);
    }

    @Override
    public void updateRole(UUID roleId, UUID userId) {
        userRepository.updateRole(roleId,userId);
    }

    @Override
    public User save(RegisterRequest registerRequest) {
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User userToSave = new User(registerRequest.getUsername(), encodedPassword, registerRequest.getEmail(),
                registerRequest.getFirstName(), registerRequest.getLastName());
        userToSave.setId(UUID.randomUUID());
        userToSave.setEnabled(false);
        return userRepository.saveAndFlush(userToSave);
    }
}
