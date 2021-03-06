package pl.martyna.catering.app.security.service;

import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                        .orElseThrow( ( ) ->
                        new UsernameNotFoundException("User Not Found with username : " + username));
        return UserPrinciple.build(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getId());
	//	userRepository.updateRole(auth.getRoles().iterator().next().getId(), auth.getId());
        if (!user.getPassword().equals("")) {
            userRepository.resetPassword(encoder.encode(user.getPassword()), user.getId());
        }
    }

}
