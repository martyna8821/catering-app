package pl.martyna.catering.app.service.order;

import pl.martyna.catering.app.entity.order.Order;

import java.util.List;

public interface IOrderService {

    Order save(Order orderToSave);
    List<Order> getAll();
}
