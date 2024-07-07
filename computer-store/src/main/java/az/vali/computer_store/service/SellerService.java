package az.vali.computer_store.service;

import az.vali.computer_store.entity.Seller;
import az.vali.computer_store.exception.NotFoundException;
import az.vali.computer_store.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new NotFoundException("Seller is not found");
        }
        sellerRepository.deleteById(id);
        return optSeller.get();
    }

    public Seller updateSeller(Seller seller) {
        Optional<Seller> optSeller = sellerRepository.findById(seller.getId());
        if (optSeller.isEmpty()) {
            throw new NotFoundException("Seller is not found");
        }
        Seller databaseSeller = sellerRepository.save(seller);
        return databaseSeller;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }
}
