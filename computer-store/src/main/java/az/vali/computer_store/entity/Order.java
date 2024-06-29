package az.vali.computer_store.entity;

import az.vali.computer_store.status.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "computerstore_orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Computer computers;
    @CreationTimestamp
    private LocalDateTime orderDate;
    @Enumerated
    private OrderStatus orderStatus;


}
