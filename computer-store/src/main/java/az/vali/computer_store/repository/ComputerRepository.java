package az.vali.computer_store.repository;

import az.vali.computer_store.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    List<Computer> findComputersByIdIn(List<Long> ids);

}
