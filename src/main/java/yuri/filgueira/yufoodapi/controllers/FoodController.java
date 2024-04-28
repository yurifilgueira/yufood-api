package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.services.FoodServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/foods")
public class FoodController {

    @Autowired
    private FoodServices services;

    @GetMapping
    public ResponseEntity<List<Food>> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Food> findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping
    public ResponseEntity<Food> create(@RequestBody Food food){
        return services.create(food);
    }

    @PutMapping
    public ResponseEntity<Food> update(@RequestBody Food food){
        return services.update(food);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return services.delete(id);
    }

}
