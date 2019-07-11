package com.martyna.catering.app.repository;

import com.martyna.catering.app.model.Role;
import com.martyna.catering.app.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    public User findByUsername(String username){
        User user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setEmail("ds");
        user.setId(1);
        user.setPassword(new BCryptPasswordEncoder().encode("123"));
        return user;
    }

    public List<User> findAll(){
        User user = new User();
        user.setUsername("user");
        user.setName("name of user");
        user.setEmail("ds");
        user.setId(1);
        user.setPassword(new BCryptPasswordEncoder().encode("123"));

        User user1 = new User();
        user1.setName("user2");
        user1.setEmail("ds");
        user1.setId(1);
        user.setPassword(new BCryptPasswordEncoder().encode("123"));

        return List.of(user,user1);

    }

  public  void delete(Integer id){

  }


   public void updateUser(String name, String userName, String email, Integer id){

   }


public void resetPassword(String password, Integer id){

}


}
