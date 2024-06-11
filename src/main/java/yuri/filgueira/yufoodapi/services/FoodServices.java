package yuri.filgueira.yufoodapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.exceptions.ResourceNotFoundException;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.FoodRepository;
import yuri.filgueira.yufoodapi.repositories.RestaurantRepository;

import java.util.List;

@Service
public class FoodServices {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private MyModelMapper mapper;

    public ResponseEntity<List<FoodVO>> findAll(Long restaurantId){

        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        List<Food> items = restaurant.getFoods().stream().toList();

        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.convertList(items, FoodVO.class));
    }

    public ResponseEntity<FoodVO> findById(Long restaurantId, Long foodId){
        var entity = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"))
                .getFoods().stream().filter(food -> food.getId().equals(foodId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        return ResponseEntity.ok(mapper.convertValue(entity, FoodVO.class));
    }

    public ResponseEntity<FoodVO> create(Long restaurantId, FoodVO foodVO){
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var entity = mapper.convertValue(foodVO, Food.class);

        restaurant.getFoods().add(entity);
        restaurantRepository.save(restaurant);

        return ResponseEntity.ok(mapper.convertValue(foodRepository.save(entity), FoodVO.class));
    }

    public ResponseEntity<FoodVO> update(Long restaurantId, FoodVO foodVO){

        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var entity = foodRepository.findById(foodVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        
        restaurant.getFoods().remove(entity);
        
        entity.setName(foodVO.getName());
        entity.setPrice(foodVO.getPrice());
        
        restaurant.getFoods().add(entity);
        restaurantRepository.save(restaurant);

        return ResponseEntity.ok(mapper.convertValue(foodRepository.save(entity), FoodVO.class));

    }

    public ResponseEntity<Void> delete(Long restaurantId, Long foodId){

        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var entity = foodRepository.findById(foodId)
                .orElseThrow(() ->new ResourceNotFoundException("Resource not found"));

        restaurant.getFoods().remove(entity);
        restaurantRepository.save(restaurant);

        foodRepository.deleteById(foodId);

        return ResponseEntity.noContent().build();
    }

}
