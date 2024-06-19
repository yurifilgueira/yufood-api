package yuri.filgueira.yufoodapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.controllers.FoodController;
import yuri.filgueira.yufoodapi.controllers.OrderController;
import yuri.filgueira.yufoodapi.controllers.RestaurantController;
import yuri.filgueira.yufoodapi.data.vo.RestaurantVO;
import yuri.filgueira.yufoodapi.entities.Restaurant;
import yuri.filgueira.yufoodapi.exceptions.ResourceNotFoundException;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.RestaurantRepository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class RestaurantServices {

    @Autowired
    private RestaurantRepository repository;
    @Autowired
    private MyModelMapper mapper;

    public ResponseEntity<List<RestaurantVO>> findAll(){
        List<RestaurantVO> restaurantVOs = mapper.convertList(repository.findAll(), RestaurantVO.class);

        if(restaurantVOs.isEmpty()){
            return ResponseEntity.notFound().build();
        }

       restaurantVOs.forEach(restaurantVO -> {
           restaurantVO.add(linkTo(methodOn(RestaurantController.class).findById(restaurantVO.getKey())).withSelfRel());
           restaurantVO.add(linkTo(methodOn(FoodController.class).findAllFoods(restaurantVO.getKey())).withRel("Foods"));
           restaurantVO.add(linkTo(methodOn(OrderController.class).findAll()).withRel("Orders"));
       });

        return ResponseEntity.ok(restaurantVOs);
    }

    public ResponseEntity<RestaurantVO> findById(Long id){
        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));
        var restaurantVO = mapper.convertValue(entity, RestaurantVO.class);

        restaurantVO.add(linkTo(methodOn(RestaurantController.class).findAll()).withRel("All Restaurants"));
        restaurantVO.add(linkTo(methodOn(FoodController.class).findAllFoods(restaurantVO.getKey())).withRel("Foods"));

        return ResponseEntity.ok(restaurantVO);

    }

    public ResponseEntity<RestaurantVO> create(RestaurantVO restaurantVO) {

        var entity = mapper.convertValue(restaurantVO, Restaurant.class);

        var vo = mapper.convertValue(repository.save(entity), RestaurantVO.class);

        vo.add(linkTo(methodOn(RestaurantController.class).create(restaurantVO)).withSelfRel());
        vo.add(linkTo(methodOn(FoodController.class).findAllFoods(restaurantVO.getKey())).withRel("Foods"));

        return ResponseEntity.ok(vo);
    }

    public ResponseEntity<RestaurantVO> update(RestaurantVO restaurantVO){

        var restaurant = mapper.convertValue(restaurantVO, Restaurant.class);
        var entity = repository.findById(restaurantVO.getKey()).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

        entity.setEmail(restaurant.getEmail());
        entity.setName(restaurant.getName());
        entity.setCnpj(restaurant.getCnpj());

        entity.getFoods().addAll(restaurant.getFoods());
        entity.getOrders().clear();
        entity.getOrders().addAll(restaurant.getOrders());
        entity.getAddresses().clear();
        entity.getAddresses().addAll(restaurant.getAddresses());

       var vo = mapper.convertValue(repository.save(entity), RestaurantVO.class);
       vo.add(linkTo(methodOn(RestaurantController.class).update(restaurantVO)).withSelfRel());
       vo.add(linkTo(methodOn(FoodController.class).findAllFoods(restaurantVO.getKey())).withRel("Foods"));

        return ResponseEntity.ok(vo);
    }

    public ResponseEntity<Void> delete(Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
