package pl.martyna.catering.app.repository.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.entity.order.Order;
import static org.assertj.core.api.Assertions.*;


import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IOrderRepository orderRepository;

    @Test
    public void testGettingUserOrders(){
        //given
        User user = new User("username", "password", "email@email.com",
                        "firstName", "lastName", "number");
        this.entityManager.persist(user);

        User diffrentUser = new User("username2", "password", "email2@email.com",
                "firstName", "lastName", "number");
        this.entityManager.persist(diffrentUser);

        Order userOrder = new Order(user);
        this.entityManager.persist(userOrder);

        Order diffrentUserOrder = new Order(diffrentUser);
        this.entityManager.persist(diffrentUserOrder);
        this.entityManager.flush();

        //when
        List<Order> userOrders = this.orderRepository.findByUser(user.getId());

        //then
        assertThat(userOrders.contains(userOrder));
        assertThat(!userOrders.contains(diffrentUserOrder));

    }
}
