package az.vali.computer_store.repository;

import az.vali.computer_store.entity.Courier;
import az.vali.computer_store.status.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findTop1ByCourierStatusOrderById(CourierStatus courierStatus);
}
