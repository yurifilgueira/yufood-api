package yuri.filgueira.yufoodapi.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.entities.OrderItem;
import yuri.filgueira.yufoodapi.repositories.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemServices {

    private OrderItemRepository repository;

    public ResponseEntity<List<OrderItem>> findAll(){
        List<OrderItem> items = repository.findAll();
        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<OrderItem> findById(Long id){
        var orderItem = repository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));

        return ResponseEntity.ok(orderItem);

    }

    public ResponseEntity<OrderItem> create(OrderItem item){
        var orderItem = repository.save(item);

        return ResponseEntity.ok(orderItem);
    }

    public ResponseEntity<OrderItem> update(OrderItem orderItem){

        var entity = repository.findById(orderItem.getId()).orElseThrow(()-> new RuntimeException("Resource not found"));

        entity.setFood(orderItem.getFood());
        entity.setQuantity(orderItem.getQuantity());
        entity.setSubtotal(orderItem.getSubtotal());

        return ResponseEntity.ok(repository.save(entity));

    }

    public ResponseEntity<Void> delete(Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
