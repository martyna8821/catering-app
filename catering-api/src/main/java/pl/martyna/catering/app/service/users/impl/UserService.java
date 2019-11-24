package pl.martyna.catering.app.service.users.impl;

import pl.martyna.catering.app.entity.auth.Role;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.exception.UserNotFoundException;
import pl.martyna.catering.app.repository.users.IAddressRepository;
import pl.martyna.catering.app.repository.auth.IPasswordResetTokenRepository;
import pl.martyna.catering.app.repository.auth.IRoleRepository;
import pl.martyna.catering.app.repository.users.IUserRepository;
import pl.martyna.catering.app.dto.auth.RegisterRequest;
import pl.martyna.catering.app.service.users.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private IRoleRepository roleRepository;
    private IAddressRepository addressRepository;

    @Autowired
    public UserService( IUserRepository userRepository,
             PasswordEncoder passwordEncoder,
             IRoleRepository roleRepository,
             IAddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
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
    public boolean existsUserByUsername(String username) {
        return this.userRepository.existsUserByUsername(username);
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
                registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getPhoneNumber());
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

        String userPassword = this.userRepository.findByUsername(username)
                                .map(User::getPassword)
                                .orElseThrow(UserNotFoundException::new);

        return Optional.of (oldPassword.equals(passwordEncoder.encode(userPassword)));
    }

    @Override
    public void deleteByEmail(String email){
        this.userRepository.deleteByEmail(email);
    }

}
