package com.martyna.catering.app.service;

import com.martyna.catering.app.dto.UserDTO;
import com.martyna.catering.app.entity.Address;
import com.martyna.catering.app.entity.Role;
import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.exception.UserNotFoundException;
import com.martyna.catering.app.repository.IAddressRepository;
import com.martyna.catering.app.repository.auth.IPasswordResetTokenRepository;
import com.martyna.catering.app.repository.auth.IRoleRepository;
import com.martyna.catering.app.repository.IUserRepository;
import com.martyna.catering.app.security.dto.RegisterRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private IRoleRepository roleRepository;
    private IAddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder,
                       IRoleRepository roleRepository, IPasswordResetTokenRepository passwordTokenRepository,
                       IAddressRepository addressRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(UUID id){
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean existsUserByUsernameOrEmail(String username, String email) {
        return userRepository.existsUserByUsernameOrEmail(username, email);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
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
    public void resetPassword(String newPassword, UUID userId) {
        userRepository.resetPassword(passwordEncoder.encode(newPassword), userId);
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
       // modelMapper.map(registerRequest.getAddress(), Address.class).
         //       .collect(Collectors.toList());
        registerRequest.getAddress().setId(UUID.randomUUID());
        addressRepository.save(registerRequest.getAddress());
        userToSave.setAddress(registerRequest.getAddress());
        return  userRepository.saveAndFlush(userToSave);
    }

    @Override
    public Optional<Boolean> validateOldPassword(String username, String oldPassword) {

        String userPassword = findByUsername(username)
                                .map(User::getPassword)
                                .orElseThrow(UserNotFoundException::new);

        return Optional.of (oldPassword.equals(passwordEncoder.encode(userPassword)));
    }

    @Override
    public void deleteByEmail(String email){
        this.userRepository.deleteByEmail(email);
    }

}
