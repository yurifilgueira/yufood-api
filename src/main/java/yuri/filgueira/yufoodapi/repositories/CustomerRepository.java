package yuri.filgueira.yufoodapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yuri.filgueira.yufoodapi.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
