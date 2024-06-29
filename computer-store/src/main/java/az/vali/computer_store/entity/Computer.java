package az.vali.computer_store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "computerstore_computers")
@Getter
@Setter
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private Integer price;
    private Boolean isNew;
    private String cpu;
    private Integer ram;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
