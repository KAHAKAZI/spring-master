package pl.training.shop.orders;

import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);

    void update(Order order);
}
