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

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Address>> findAll(@PathVariable("userId") Long userId) {
        return services.findAll(userId);
    }

    @GetMapping(value = "/{userId}/{addressId}")
    public ResponseEntity<Address> findById(@PathVariable("userId") Long userId, @PathVariable("addressId") Long addressId) {
        return services.findById(userId, addressId);
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<Address> create(@PathVariable("userId") Long userId, @RequestBody Address address) {
        return services.create(userId, address);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<Address> update(@PathVariable("userId") Long userId, @RequestBody Address address) {
        return services.update(userId, address);
    }

    @DeleteMapping(value = "/{userId}/{addressId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("addressId") Long addressId) {
        return services.delete(userId, addressId);
    }

}
