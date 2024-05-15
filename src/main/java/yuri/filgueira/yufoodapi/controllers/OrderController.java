package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.entities.Order;
import yuri.filgueira.yufoodapi.services.OrderServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(Long orderId) {
        return orderServices.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id) {
        return orderServices.findById(id);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return this.orderServices.create(order);
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody Order order) {
        return this.orderServices.update(order);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        return orderServices.delete(id);
    }
}
