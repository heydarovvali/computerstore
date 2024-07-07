package az.vali.computer_store.service;

import az.vali.computer_store.entity.Basket;
import az.vali.computer_store.exception.NotFoundException;
import az.vali.computer_store.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;

    public Basket addBasketItem(Basket basket) {
        basket.setId((long) (0));
        Basket databaseBasket = basketRepository.save(basket);
        return databaseBasket;
    }

    public Basket deleteBasket(Long id) {
        Optional<Basket> optBasket = basketRepository.findById(id);
        if (optBasket.isEmpty()) {
            throw new NotFoundException("Basket is not found");
        }
        basketRepository.deleteById(id);
        return optBasket.get();
    }

}
