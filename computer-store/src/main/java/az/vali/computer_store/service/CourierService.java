package az.vali.computer_store.service;

import az.vali.computer_store.entity.Courier;
import az.vali.computer_store.exception.NotFoundException;
import az.vali.computer_store.repository.ComputerRepository;
import az.vali.computer_store.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourierService {
    @Autowired
    private CourierRepository courierRepository;

    public Courier addCourier(Courier courier) {
        courier.setId((long) (0));
        Courier databaseCourier = courierRepository.save(courier);
        return databaseCourier;
    }

    public Courier deleteCourier(Long id) {
        Optional<Courier> optCourier = courierRepository.findById(id);
        if (optCourier.isEmpty()) {
            throw new NotFoundException("Courier is not found");
        }
        courierRepository.deleteById(id);
        return optCourier.get();
    }

    public List<Courier> getAllCourier() {
        return courierRepository.findAll();
    }
}
