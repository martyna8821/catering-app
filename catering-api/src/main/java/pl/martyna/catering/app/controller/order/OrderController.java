package pl.martyna.catering.app.controller.order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.dto.input.DietInput;
import pl.martyna.catering.app.dto.input.OrderInput;
import pl.martyna.catering.app.dto.resource.DietResource;
import pl.martyna.catering.app.dto.resource.OrderResource;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.order.Order;
import pl.martyna.catering.app.service.diet.IDietService;
import pl.martyna.catering.app.service.order.IOrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/orders")
public class OrderController {

    IOrderService orderService;
    private ModelMapper modelMapper;

    @Autowired
    public OrderController(IOrderService orderService, ModelMapper modelMapper){
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public Order addDiet(@RequestBody OrderInput orderToCreate){
        Order order= modelMapper.map(orderToCreate, Order.class);
        return this.orderService.save(order);
    }

    @GetMapping
    public List<OrderResource> getAllDiets(){
        List<Order> orders = this.orderService.getAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResource.class))
                .collect(Collectors.toList());
    }


}
