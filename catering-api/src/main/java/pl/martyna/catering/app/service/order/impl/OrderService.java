package pl.martyna.catering.app.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.order.Order;
import pl.martyna.catering.app.repository.order.IOrderRepository;
import pl.martyna.catering.app.service.order.IOrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order orderToSave) {
        orderToSave.setOrderDate(LocalDate.now());
        return this.orderRepository.save(orderToSave);
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> getUserOrders(UUID userId) {
        return this.orderRepository.findByUser(userId);
    }
}
