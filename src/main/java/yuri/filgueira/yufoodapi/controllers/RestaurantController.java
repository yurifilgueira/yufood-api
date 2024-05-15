package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.entities.Restaurant;
import yuri.filgueira.yufoodapi.services.RestaurantServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantServices services;

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable("id") Long id) {
        return services.findById(id);
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        return services.create(restaurant);
    }

    @PutMapping
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant) {
        return services.update(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return services.delete(id);
    }



}
