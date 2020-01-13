package pl.martyna.catering.app.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.order.Order;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<Order, UUID> {

    @Transactional
    @Query(value = "select * from orders o where o.client_user_id = ?1 ",
            nativeQuery = true)
    List<Order> findByUser(UUID userId);
}
