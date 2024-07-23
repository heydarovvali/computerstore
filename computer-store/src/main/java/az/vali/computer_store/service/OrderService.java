package az.vali.computer_store.service;

import az.vali.computer_store.entity.*;
import az.vali.computer_store.entity.dto.OrderDto;
import az.vali.computer_store.entity.dto.mapper.Mapper;
import az.vali.computer_store.exception.DeliveryProblemBecauseOfOrderStatusException;
import az.vali.computer_store.exception.NotFoundException;
import az.vali.computer_store.repository.BasketRepository;
import az.vali.computer_store.repository.CourierRepository;
import az.vali.computer_store.repository.OrderRepository;
import az.vali.computer_store.repository.UserRepository;
import az.vali.computer_store.status.CourierStatus;
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

    @Autowired
    private CourierRepository courierRepository;

    public OrderDto addOrder(Long userId) {
        Optional<User> optUser = userRepository.findById(userId); // birden clientin yazdigi user movcud olmaz.
        if (optUser.isEmpty()) {
            throw new NotFoundException("User is not found,cannot create order");
        }
        Optional<Basket> optBasket = basketRepository.findTop1ByUserIdOrderByIdDesc(userId);
        if (optBasket.isEmpty()) {
            throw new NotFoundException("Basket is not found,cannot create order");
        }
        Basket basket = optBasket.get();
        List<Computer> computers = basket.getComputers();

        Order order = new Order();
        order.setId((long) 0);
        order.setOrderDate(LocalDateTime.now());
        order.setComputers(computers);
        order.setOrderStatus(OrderStatus.SOLD);
        order.setUser(optUser.get());

        Order databaseOrder = orderRepository.save(order);
        clearBasket(userId);
        return Mapper.entityToOrderDto(databaseOrder);

    }

    public Order findCourierForOrder(Long orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        if (optOrder.isEmpty()) {
            throw new NotFoundException("Order is not found");
        }
        Order order = optOrder.get();
        if (order.getOrderStatus() != OrderStatus.SOLD) {
            throw new DeliveryProblemBecauseOfOrderStatusException("Order cannot delivered,because status is not SOLD");
        }
        Optional<Courier> optCourier = courierRepository.findTop1ByCourierStatusOrderById(CourierStatus.FREE);
        if (optCourier.isEmpty()) {
            throw new NotFoundException("Courier is not found");
        }
        Courier courier = optCourier.get();
        order.setOrderStatus(OrderStatus.DELIVERY);
        courier.setCourierStatus(CourierStatus.ON_DELIVERY);
        Courier databaseCourier = courierRepository.save(courier);
        order.setCourier(databaseCourier);
        Order databaseOrder = orderRepository.save(order);
        return databaseOrder;
    }

    public Order orderDelivered(Long orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        if (optOrder.isEmpty()) {
            throw new NotFoundException("Order is not found");
        }
        Order order = optOrder.get();
        order.setOrderStatus(OrderStatus.DELIVERED);
        Optional<Courier> optCourier = courierRepository.findById(order.getCourier().getId());
        if (optCourier.isEmpty()) {
            throw new NotFoundException("Courier is not found");
        }
        Courier courier = optCourier.get();
        courier.setCourierStatus(CourierStatus.FREE);
        Courier databaseCourier = courierRepository.save(courier);
        order.setCourier(databaseCourier);
        return orderRepository.save(order);
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
