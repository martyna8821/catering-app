package pl.martyna.catering.app.controller.order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/orders")
public class OrderController {

    private IOrderService orderService;
    private ModelMapper modelMapper;

    @Autowired
    public OrderController(IOrderService orderService, ModelMapper modelMapper){
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderInput orderToCreate){
        Order savedOrder = this.orderService.save( modelMapper.map(orderToCreate, Order.class) );
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllDiets(){
        List<OrderResource> ordersList = this.orderService.getAll()
                                                            .stream()
                                                            .map(order ->
                                                                    modelMapper.map(order, OrderResource.class))
                                                            .collect(Collectors.toList());

        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserOrders(@PathVariable UUID id){
        List<OrderResource> userOrdersList = this.orderService.getUserOrders(id)
                                                            .stream()
                                                            .map(order ->
                                                                    modelMapper.map(order, OrderResource.class))
                                                            .collect(Collectors.toList());

        return new ResponseEntity<>(userOrdersList, HttpStatus.OK);

     }
}
