package yuri.filgueira.yufoodapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.repositories.FoodRepository;
import yuri.filgueira.yufoodapi.repositories.RestaurantRepository;

import java.util.List;

@Service
public class FoodServices {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodRepository foodRepository;

    public ResponseEntity<List<Food>> findAll(Long restaurantId){

        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        List<Food> items = restaurant.getFoods().stream().toList();

        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<Food> findById(Long restaurantId, Long foodId){
        var entity = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Resource not found"))
                .getFoods().stream().filter(food -> food.getId().equals(foodId)).findFirst().orElseThrow(() -> new RuntimeException("Resource not found"));

        return ResponseEntity.ok(entity);
    }

    public ResponseEntity<Food> create(Long restaurantId, Food food){
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        restaurant.getFoods().add(food);
        restaurantRepository.save(restaurant);

        return ResponseEntity.ok(foodRepository.save(food));
    }

    public ResponseEntity<Food> update(Long restaurantId, Food food){

        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        var entity = foodRepository.findById(food.getId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        
        restaurant.getFoods().remove(entity);
        
        entity.setName(food.getName());
        entity.setPrice(food.getPrice());
        
        restaurant.getFoods().add(entity);
        restaurantRepository.save(restaurant);

        return ResponseEntity.ok(foodRepository.save(entity));

    }

    public ResponseEntity<Void> delete(Long restaurantId, Long foodId){

        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        var entity = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        restaurant.getFoods().remove(entity);
        restaurantRepository.save(restaurant);

        foodRepository.deleteById(foodId);

        return ResponseEntity.noContent().build();
    }

}
