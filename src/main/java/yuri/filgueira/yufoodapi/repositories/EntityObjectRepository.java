package yuri.filgueira.yufoodapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yuri.filgueira.yufoodapi.entities.EntityObject;

public interface EntityObjectRepository extends JpaRepository<EntityObject, Long> {
}
