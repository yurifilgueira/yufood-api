package yuri.filgueira.yufoodapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.controllers.FoodController;
import yuri.filgueira.yufoodapi.controllers.RestaurantController;
import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.exceptions.ResourceNotFoundException;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.FoodRepository;
import yuri.filgueira.yufoodapi.repositories.RestaurantRepository;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

        var items = restaurant.getFoods().stream().toList();

        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var foodVOs = mapper.convertList(items, FoodVO.class);
        foodVOs.forEach(food ->
                food.add(linkTo(methodOn(FoodController.class)
                        .findFoodById(restaurantId, food.getKey())).withSelfRel())
        );

        return ResponseEntity.ok(foodVOs);
    }

    public ResponseEntity<FoodVO> findById(Long restaurantId, Long foodId){
        var entity = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"))
                .getFoods().stream().filter(food -> food.getId().equals(foodId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var vo = mapper.convertValue(entity, FoodVO.class);
        vo.add(linkTo(methodOn(FoodController.class).findAllFoods(restaurantId)).withRel("All foods"));
        vo.add(linkTo(methodOn(RestaurantController.class).findById(restaurantId)).withRel("Restaurant"));

        return ResponseEntity.ok(vo);
    }

    public ResponseEntity<FoodVO> create(Long restaurantId, FoodVO foodVO){
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var entity = mapper.convertValue(foodVO, Food.class);

        restaurant.getFoods().add(entity);
        restaurantRepository.save(restaurant);

        var vo = mapper.convertValue(foodRepository.save(entity), FoodVO.class);
        vo.add(linkTo(methodOn(FoodController.class).findAllFoods(restaurantId)).withRel("All foods"));
        vo.add(linkTo(methodOn(RestaurantController.class).findById(restaurantId)).withRel("Restaurant"));

        return ResponseEntity.ok(vo);
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

        var vo = mapper.convertValue(foodRepository.save(entity), FoodVO.class);
        vo.add(linkTo(methodOn(FoodController.class).findAllFoods(restaurantId)).withRel("All foods"));
        vo.add(linkTo(methodOn(RestaurantController.class).findById(restaurantId)).withRel("Restaurant"));

        return ResponseEntity.ok(vo);

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
