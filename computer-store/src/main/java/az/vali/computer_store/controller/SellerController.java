package az.vali.computer_store.controller;

import az.vali.computer_store.entity.Seller;
import az.vali.computer_store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping("/addSeller")
    public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
        Seller databaseSeller = sellerService.addSeller(seller);
        return ResponseEntity.status(201).body(databaseSeller);
    }

    @PutMapping("/updateSeller")
    public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller) {
        Seller databaseSeller = sellerService.updateSeller(seller);
        return ResponseEntity.status(200).body(databaseSeller);
    }

    @DeleteMapping("/deleteSeller/{id}")
    public ResponseEntity<Seller> deleteSeller(@PathVariable Long id) {
        Seller databaseSeller = sellerService.deleteSeller(id);
        return ResponseEntity.status(200).body(databaseSeller);
    }

    @GetMapping("/getAllSeller")
    public ResponseEntity<List<Seller>> getAllSellers() {
        return ResponseEntity.status(200).body(sellerService.getAllSellers());
    }
}
