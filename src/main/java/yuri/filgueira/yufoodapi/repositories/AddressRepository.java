package yuri.filgueira.yufoodapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yuri.filgueira.yufoodapi.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
