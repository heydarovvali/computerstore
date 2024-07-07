package az.vali.computer_store.service;

import az.vali.computer_store.entity.Computer;
import az.vali.computer_store.exception.NotFoundException;
import az.vali.computer_store.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    public Computer addComputer(Computer computer) {
        computer.setId((long) (0));
        Computer databaseComputer = computerRepository.save(computer);
        return databaseComputer;
    }

    public Computer deleteComputer(Long id) {
        Optional<Computer> optComputer = computerRepository.findById(id);
        if (optComputer.isEmpty()) {
            throw new NotFoundException("Computer is not found");
        }
        computerRepository.deleteById(id);
        return optComputer.get();
    }

    public Computer updateComputer(Computer computer) {
        Optional<Computer> optComputer = computerRepository.findById(computer.getId());
        if (optComputer.isEmpty()) {
            throw new NotFoundException("Computer is not found");
        }
        Computer databaseComputer = computerRepository.save(computer);
        return databaseComputer;
    }

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }
}
