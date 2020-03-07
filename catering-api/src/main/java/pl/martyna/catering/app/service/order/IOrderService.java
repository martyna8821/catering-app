package pl.martyna.catering.app.service.order;

import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.order.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IOrderService {

    Order save(Order orderToSave);
    List<Order> getAll();
    List<Order> getUserOrders(UUID userId);
    int getOrderedMenusNumber(Menu menu);
}
