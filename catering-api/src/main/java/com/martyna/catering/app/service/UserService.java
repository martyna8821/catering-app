package com.martyna.catering.app.service;

import com.martyna.catering.app.entity.Role;
import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.repository.auth.IRoleRepository;
import com.martyna.catering.app.repository.auth.IUserRepository;
import com.martyna.catering.app.security.dto.RegisterRequest;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private IRoleRepository roleRepository;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder,
                       IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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

        Set<Role> roles = new HashSet<>();
        registerRequest.getRoles().forEach(role ->
            roles.add( roleRepository
                        .findByRole(role)
                        .orElseThrow(( () ->
                                new RuntimeException("Invalid user role: "+  role))))
        );

       userToSave.setRoles(roles);

       return userRepository.saveAndFlush(userToSave);
    }
}
