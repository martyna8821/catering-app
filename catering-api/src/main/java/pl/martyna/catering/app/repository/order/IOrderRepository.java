package pl.martyna.catering.app.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.martyna.catering.app.entity.order.Order;


import java.util.List;
import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByClient_Id(UUID id);
}
