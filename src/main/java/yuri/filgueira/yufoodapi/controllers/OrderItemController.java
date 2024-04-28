package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.entities.OrderItem;
import yuri.filgueira.yufoodapi.services.OrderItemServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemServices services;

    @GetMapping
    public ResponseEntity<List<OrderItem>> findAll(Long orderId){
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItem> findById(@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem item){
        return services.create(item);
    }

    @PutMapping
    public ResponseEntity<OrderItem> update(@RequestBody OrderItem item){
        return services.update(item);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return services.delete(id);
    }
}
