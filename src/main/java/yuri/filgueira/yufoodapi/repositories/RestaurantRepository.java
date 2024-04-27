package yuri.filgueira.yufoodapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yuri.filgueira.yufoodapi.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
