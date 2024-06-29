package az.vali.computer_store.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ComputerDto {
    private Long id;
    private String name;
    private String brand;
    private Integer price;
}
