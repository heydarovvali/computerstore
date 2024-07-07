package az.vali.computer_store.service;

import az.vali.computer_store.entity.Order;
import az.vali.computer_store.exception.NotFoundException;
import az.vali.computer_store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order addOrder(Order order) {
        order.setId((long) (0));
        Order databaseOrder = orderRepository.save(order);
        return databaseOrder;
    }

    public Order updateOrder(Order order) {
        if (order.getId() != null) {
            Optional<Order> optOrder = orderRepository.findById(order.getId());
            if (optOrder.isEmpty()) {
                throw new NotFoundException("Order is not found");
            }
            Order databaseOrder = orderRepository.save(order);
            return databaseOrder;
        } else {
            return null;
        }
    }
}
