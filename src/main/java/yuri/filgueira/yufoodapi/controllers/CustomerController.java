package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.entities.Customer;
import yuri.filgueira.yufoodapi.services.CustomerServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServices services;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        return services.findById(id);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return services.create(customer);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        return services.update(customer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return services.delete(id);
    }
}
