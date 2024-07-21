package az.vali.computer_store.service;

import az.vali.computer_store.entity.Basket;
import az.vali.computer_store.entity.Computer;
import az.vali.computer_store.entity.User;
import az.vali.computer_store.entity.dto.BasketDto;
import az.vali.computer_store.entity.dto.mapper.Mapper;
import az.vali.computer_store.exception.NotFoundException;
import az.vali.computer_store.repository.BasketRepository;
import az.vali.computer_store.repository.ComputerRepository;
import az.vali.computer_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private UserRepository userRepository;

    public BasketDto addBasketItem(BasketDto basketDto) {  // bu metodun meqsedi database-e melumati elave etmek ucundur
        List<Computer> computers = basketDto.getComputers();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < computers.size(); i++) {
            Computer computer = computers.get(i);
            Optional<Computer> optComputer = computerRepository.findById(computer.getId());
            if (optComputer.isEmpty()) {
                throw new NotFoundException("Computer is not found");
            }
            ids.add(computer.getId());
        }
        List<Computer> userComputers = computerRepository.findComputersByIdIn(ids);
        Optional<Basket> optBasket = basketRepository.findTop1ByUserIdOrderByIdDesc(basketDto.getUser().getId()); // besketin listini icindeki komputer sayina gore azalan sira ile siralayir.
        Basket basket = null;
        if (optBasket.isPresent()) {
            basket = optBasket.get();
        } else {
            basket = new Basket();
            basket.setId((long) (0));
            Optional<User> optUser = userRepository.findById(basketDto.getUser().getId());
            if(optUser.isEmpty()){
                throw new NotFoundException("basket cannot be created,user is not found");
            }
            basket.setUser(optUser.get());
            basket.setCreatedTime(LocalDateTime.now());
            basket.setExpireTime(LocalDateTime.now().plusDays(5));
        }
        basket.setComputers(userComputers);
        Basket databaseBasket = basketRepository.save(basket); // database basket elave edilir
        return Mapper.entityToBasketDto(databaseBasket);

    }


//    public Basket deleteBasket(Long id) {
//        Optional<Basket> optBasket = basketRepository.findById(id);
//        if (optBasket.isEmpty()) {
//            throw new NotFoundException("Basket is not found");
//        }
//        basketRepository.deleteById(id);
//        return optBasket.get();
//    }

}
