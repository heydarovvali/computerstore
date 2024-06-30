package az.vali.computer_store.service;

import az.vali.computer_store.entity.Seller;
import az.vali.computer_store.exception.SellerNotFoundException;
import az.vali.computer_store.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public Seller addSeller(Seller seller) {
        seller.setId((long) 0);
        Seller databaseSeller = sellerRepository.save(seller);
        return databaseSeller;
    }

    public Seller deleteSeller(Long id) {
        Optional<Seller> optSeller = sellerRepository.findById(id);
        if (optSeller.isEmpty()) {
            new SellerNotFoundException("Seller is not found");
        }
        sellerRepository.deleteById(id);
        return optSeller.get();
    }
}
