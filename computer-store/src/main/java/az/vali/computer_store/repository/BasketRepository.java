package az.vali.computer_store.repository;

import az.vali.computer_store.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findTop1ByUserIdOrderByIdDesc(Long userId);

    List<Basket> findAllByUserId(Long userId);
}
