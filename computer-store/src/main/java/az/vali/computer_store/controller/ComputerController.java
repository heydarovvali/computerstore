package az.vali.computer_store.controller;

import az.vali.computer_store.entity.Computer;
import az.vali.computer_store.entity.Courier;
import az.vali.computer_store.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @PostMapping("/addComputer")
    public ResponseEntity<Computer> addComputer(@RequestBody Computer computer) {
        Computer databaseComputer = computerService.addComputer(computer);
        return ResponseEntity.status(201).body(databaseComputer);
    }

    @PutMapping("/updateComputer")
    public ResponseEntity<Computer> updateComputer(@RequestBody Computer computer) {
        Computer databaseComputer = computerService.updateComputer(computer);
        return ResponseEntity.status(200).body(databaseComputer);
    }

    @DeleteMapping("/deleteComputer/{id}")
    public ResponseEntity<Computer> deleteComputer(@PathVariable Long id) {
        Computer databaseComputer = computerService.deleteComputer(id);
        return ResponseEntity.status(200).body(databaseComputer);
    }

    @GetMapping("/getAllComputers")
    public ResponseEntity<List<Computer>> getAllComputers() {
        return ResponseEntity.status(200).body(computerService.getAllComputers());
    }
}
