package az.vali.computer_store.controller;

import az.vali.computer_store.entity.Basket;
import az.vali.computer_store.entity.dto.BasketDto;
import az.vali.computer_store.repository.BasketRepository;
import az.vali.computer_store.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping("/addBasket")
    public ResponseEntity<BasketDto> addBasket(@RequestBody BasketDto basketDto) {
        BasketDto databaseBasket = basketService.addBasketItem(basketDto);
        return ResponseEntity.status(201).body(databaseBasket);
    }
}
