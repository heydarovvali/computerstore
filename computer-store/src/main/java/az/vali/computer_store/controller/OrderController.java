package az.vali.computer_store.controller;

import az.vali.computer_store.entity.Order;
import az.vali.computer_store.entity.dto.OrderDto;
import az.vali.computer_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder/{userId}")
    public ResponseEntity<OrderDto> addOrder(@PathVariable long userId) {
        OrderDto databaseOrder = orderService.addOrder(userId);
        return ResponseEntity.status(201).body(databaseOrder);
    }

    @GetMapping("/findCourierOrderId/{orderId}")
    public ResponseEntity<Order> getFindCourierOrderId(@PathVariable long orderId) {
        Order databaseOrder = orderService.findCourierForOrder(orderId);
        return ResponseEntity.status(200).body(databaseOrder);
    }
}
