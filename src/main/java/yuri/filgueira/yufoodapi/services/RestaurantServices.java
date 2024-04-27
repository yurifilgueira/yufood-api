package yuri.filgueira.yufoodapi.services;

import org.springframework.http.ResponseEntity;
import yuri.filgueira.yufoodapi.entities.Restaurant;
import yuri.filgueira.yufoodapi.repositories.RestaurantRepository;

import java.util.List;

public class RestaurantServices {

    private RestaurantRepository repository;

    public ResponseEntity<List<Restaurant>> findAll(){
        List<Restaurant> items = repository.findAll();
        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<Restaurant> findById(Long id){
        var restaurant = repository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));

        return ResponseEntity.ok(restaurant);

    }

    public ResponseEntity<Restaurant> create(Restaurant item){
        var restaurant = repository.save(item);

        return ResponseEntity.ok(restaurant);
    }

    public ResponseEntity<Restaurant> update(Restaurant restaurant){

        var entity = repository.findById(restaurant.getId()).orElseThrow(()-> new RuntimeException("Resource not found"));

        entity.setEmail(restaurant.getEmail());
        entity.setName(restaurant.getName());
        entity.setCnpj(restaurant.getCnpj());
        entity.setFoods(restaurant.getFoods());
        entity.setOrders(restaurant.getOrders());
        entity.setAddresses(restaurant.getAddresses());

        return ResponseEntity.ok(repository.save(entity));

    }

    public ResponseEntity<Void> delete(Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
