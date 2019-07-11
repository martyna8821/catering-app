package com.martyna.catering.app.repository;

import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.repository.auth.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

  //  @Autowired
//    private TestEntityManager entityManager;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void chechExstsBy() {
        // given
        User alex = new User("alex", "123", "as@mail.com", "as", "as");
       // entityManager.persist(alex);
       // entityManager.flush();

        boolean a = userRepository.existsUserByUsernameOrEmail("admin","admin@mail.com");
        boolean b = userRepository.existsUserByUsernameOrEmail("as","admin@mail.com");
        boolean c = userRepository.existsUserByUsernameOrEmail("as","as@mail.com");
        assertThat(a).isEqualTo(true);
        assertThat(b).isEqualTo(true);
        assertThat(c).isEqualTo(false);

    }


}
