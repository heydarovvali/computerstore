package az.vali.computer_store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
@Entity(name = "computerstore_basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<Computer> computers;
    @CreationTimestamp
    private LocalDateTime createdTime;
    private LocalDateTime expireTime;
}
