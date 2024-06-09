package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.data.vo.RestaurantVO;
import yuri.filgueira.yufoodapi.entities.Restaurant;
import yuri.filgueira.yufoodapi.services.RestaurantServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantServices services;

    @GetMapping
    public ResponseEntity<List<RestaurantVO>> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantVO> findById(@PathVariable("id") Long id) {
        return services.findById(id);
    }

    @PostMapping
    public ResponseEntity<RestaurantVO> create(@RequestBody RestaurantVO restaurant) {
        return services.create(restaurant);
    }

    @PutMapping
    public ResponseEntity<RestaurantVO> update(@RequestBody RestaurantVO restaurant) {
        return services.update(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return services.delete(id);
    }



}
