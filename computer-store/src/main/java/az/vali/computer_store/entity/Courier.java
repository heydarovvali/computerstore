package az.vali.computer_store.entity;

import az.vali.computer_store.status.CourierStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

@Entity(name = "computerstore_couriers")
public class Courier {
    private Long id;
    private String name;
    @Enumerated
    private CourierStatus courierStatus;
}
