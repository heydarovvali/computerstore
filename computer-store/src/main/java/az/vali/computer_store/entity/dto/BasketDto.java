package az.vali.computer_store.entity.dto;

import az.vali.computer_store.entity.Computer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BasketDto {
    private Long id;
    private List<Computer> computers;
}
