package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.entities.Address;
import yuri.filgueira.yufoodapi.services.AddressServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/addresses")
public class AddressController {

    @Autowired
    private AddressServices services;

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable("id") Long id) {
        return services.findById(id);
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address) {
        return services.create(address);
    }

    @PutMapping
    public ResponseEntity<Address> update(@RequestBody Address address) {
        return services.update(address);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return services.delete(id);
    }

}
