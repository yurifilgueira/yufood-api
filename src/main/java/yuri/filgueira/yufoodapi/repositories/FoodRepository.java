package yuri.filgueira.yufoodapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yuri.filgueira.yufoodapi.entities.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
