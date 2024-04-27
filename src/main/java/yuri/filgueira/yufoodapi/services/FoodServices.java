package yuri.filgueira.yufoodapi.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.repositories.FoodRepository;

import java.util.List;

@Service
public class FoodServices {

    private FoodRepository repository;

    public ResponseEntity<List<Food>> findAll(){
        List<Food> items = repository.findAll();
        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<Food> findById(Long id){
        var food = repository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));

        return ResponseEntity.ok(food);

    }

    public ResponseEntity<Food> create(Food item){
        var food = repository.save(item);

        return ResponseEntity.ok(food);
    }

    public ResponseEntity<Food> update(Food food){

        var entity = repository.findById(food.getId()).orElseThrow(()-> new RuntimeException("Resource not found"));

        entity.setName(food.getName());
        entity.setPrice(food.getPrice());

        return ResponseEntity.ok(repository.save(entity));

    }

    public ResponseEntity<Void> delete(Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
