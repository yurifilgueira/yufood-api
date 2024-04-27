package yuri.filgueira.yufoodapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yuri.filgueira.yufoodapi.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
