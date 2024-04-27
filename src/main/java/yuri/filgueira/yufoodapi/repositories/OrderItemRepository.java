package yuri.filgueira.yufoodapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yuri.filgueira.yufoodapi.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
