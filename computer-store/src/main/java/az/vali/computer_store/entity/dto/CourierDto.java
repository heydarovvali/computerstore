package az.vali.computer_store.entity.dto;

import az.vali.computer_store.status.CourierStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourierDto {
    private Long id;
    private String name;
    private CourierStatus courierStatus;

}
