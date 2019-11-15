package pl.martyna.catering.app.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.repository.order.IOrderRepository;
import pl.martyna.catering.app.service.order.IOrderService;

@Service
public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
