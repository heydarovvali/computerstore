package az.vali.computer_store.service;

import az.vali.computer_store.entity.Basket;
import az.vali.computer_store.entity.Computer;
import az.vali.computer_store.entity.Order;
import az.vali.computer_store.entity.User;
import az.vali.computer_store.entity.dto.OrderDto;
import az.vali.computer_store.entity.dto.mapper.Mapper;
import az.vali.computer_store.exception.NotFoundException;
import az.vali.computer_store.repository.BasketRepository;
import az.vali.computer_store.repository.OrderRepository;
import az.vali.computer_store.repository.UserRepository;
import az.vali.computer_store.status.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasketRepository basketRepository;

    public OrderDto addOrder(Long userId) {
        Optional<User> optUser = userRepository.findById(userId); // birden clientin yazdigi user movcud olmaz.
        if (optUser.isEmpty()) {
            throw new NotFoundException("User is not found,cannot create order");
        }
        Optional<Basket> optBasket = basketRepository.findTop1ByUserIdOrderByIdDesc(userId);
        if (optUser.isEmpty()) {
            throw new NotFoundException("Basket is not found,cannot create order");
        }
        Basket basket = optBasket.get();
        List<Computer> computers = basket.getComputers();
        System.out.println(computers);
        Order order = new Order();
        order.setId((long) 0);
        order.setOrderDate(LocalDateTime.now());
        order.setComputers(computers);
        order.setOrderStatus(OrderStatus.SOLD);
        order.setUser(optUser.get());
        System.out.println(order);
        Order databaseOrder = orderRepository.save(order);
        clearBasket(userId);
        return Mapper.entityToOrderDto(databaseOrder);

    }

    public void clearBasket(long userId) {
        List<Basket> databaseBaskets = basketRepository.findAllByUserId(userId);
        for (int i = 0; i < databaseBaskets.size(); i++) {
            Basket basket = databaseBaskets.get(i);
            basketRepository.deleteById(basket.getId());
        }
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
