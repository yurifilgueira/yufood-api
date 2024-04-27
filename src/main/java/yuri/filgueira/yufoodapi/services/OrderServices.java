package yuri.filgueira.yufoodapi.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.entities.Order;
import yuri.filgueira.yufoodapi.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderServices {

    private OrderRepository repository;

    public ResponseEntity<List<Order>> findAll(){
        List<Order> items = repository.findAll();
        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<Order> findById(Long id){
        var order = repository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));

        return ResponseEntity.ok(order);

    }

    public ResponseEntity<Order> create(Order item){
        var order = repository.save(item);

        return ResponseEntity.ok(order);
    }

    public ResponseEntity<Order> update(Order order){

        var entity = repository.findById(order.getId()).orElseThrow(()-> new RuntimeException("Resource not found"));

        entity.setOrderItems(order.getOrderItems());
        entity.setTotal(order.getTotal());

        return ResponseEntity.ok(repository.save(entity));

    }

    public ResponseEntity<Void> delete(Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
