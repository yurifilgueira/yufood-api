package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.services.FoodServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/restaurants/{restaurantId}/foods")
public class FoodController {

    @Autowired
    FoodServices foodServices;

    @GetMapping
    public ResponseEntity<List<FoodVO>> findAllOrderItems(@PathVariable("restaurantId") Long restaurantId) {
        return foodServices.findAll(restaurantId);
    }

    @GetMapping(value = "/{foodId}")
    public ResponseEntity<FoodVO> findOrderItemById(@PathVariable("restaurantId") Long restaurantId, @PathVariable("foodId") Long foodId) {
        return foodServices.findById(restaurantId, foodId);
    }

    @PostMapping
    public ResponseEntity<FoodVO> createOrderItem(@PathVariable("restaurantId") Long restaurantId, @RequestBody Food food) {
        return foodServices.create(restaurantId, food);
    }

    @PutMapping
    public ResponseEntity<FoodVO> updateOrderItem(@PathVariable("restaurantId") Long restaurantId, @RequestBody Food food) {
        return foodServices.update(restaurantId, food);
    }

    @DeleteMapping(value = "/{foodId}")
    public ResponseEntity<Void> delete(@PathVariable("restaurantId") Long restaurantId, @PathVariable Long  foodId) {
        return foodServices.delete(restaurantId, foodId);
    }
}
