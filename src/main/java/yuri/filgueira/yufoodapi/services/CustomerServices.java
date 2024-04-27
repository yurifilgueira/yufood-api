package yuri.filgueira.yufoodapi.services;

import org.springframework.http.ResponseEntity;
import yuri.filgueira.yufoodapi.entities.Customer;
import yuri.filgueira.yufoodapi.repositories.CustomerRepository;

import java.util.List;

public class CustomerServices {

    private CustomerRepository repository;

    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> items = repository.findAll();
        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<Customer> findById(Long id){
        var customer = repository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));

        return ResponseEntity.ok(customer);

    }

    public ResponseEntity<Customer> create(Customer item){
        var customer = repository.save(item);

        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Customer> update(Customer customer){

        var entity = repository.findById(customer.getId()).orElseThrow(()-> new RuntimeException("Resource not found"));

        entity.setEmail(customer.getEmail());
        entity.setName(customer.getName());
        entity.setCpf(customer.getCpf());
        entity.setOrders(customer.getOrders());
        entity.setAddresses(customer.getAddresses());

        return ResponseEntity.ok(repository.save(entity));

    }

    public ResponseEntity<Void> delete(Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
