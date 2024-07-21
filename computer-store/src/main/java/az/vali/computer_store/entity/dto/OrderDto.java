package az.vali.computer_store.entity.dto;

import az.vali.computer_store.entity.Computer;
import az.vali.computer_store.entity.Courier;
import az.vali.computer_store.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderDto {
    private Long id;
    private List<Computer> computers;
    private User user;
    private Courier courier;
}
