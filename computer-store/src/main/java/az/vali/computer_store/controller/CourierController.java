package az.vali.computer_store.controller;

import az.vali.computer_store.entity.Courier;
import az.vali.computer_store.service.CourierService;
import az.vali.computer_store.status.CourierStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courier")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @PostMapping("/addCourier")
    public ResponseEntity<Courier> addCourier(@RequestBody Courier courier) {
        courier.setCourierStatus(CourierStatus.FREE);
        Courier databaseCourier = courierService.addCourier(courier);
        return ResponseEntity.status(201).body(databaseCourier);
    }

    @DeleteMapping("/deleteCourier/{id}")
    public ResponseEntity<Courier> deleteCourier(@PathVariable Long id) {
        Courier databaseCourier = courierService.deleteCourier(id);
        return ResponseEntity.status(200).body(databaseCourier);
    }

    @GetMapping("/getAllCourier")
    public ResponseEntity<List<Courier>> getAllCourier() {
        return ResponseEntity.status(200).body(courierService.getAllCourier());
    }
}
