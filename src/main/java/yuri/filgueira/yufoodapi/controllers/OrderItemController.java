package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.services.OrderItemServices;

import java.util.List;

@RestController
@RequestMapping(value = "orders/{orderId}/order-items")
public class OrderItemController {

    @Autowired
    OrderItemServices orderItemServices;

    @GetMapping
    public ResponseEntity<List<yuri.filgueira.yufoodapi.entities.OrderItem>> findAllOrderItems(@PathVariable("orderId") Long orderId) {
        return orderItemServices.findAll(orderId);
    }

    @GetMapping(value = "/{orderItemId}")
    public ResponseEntity<yuri.filgueira.yufoodapi.entities.OrderItem> findOrderItemById(@PathVariable("orderId") Long orderId, @PathVariable("orderItemId") Long orderItemId) {
        return orderItemServices.findById(orderId, orderItemId);
    }

    @PostMapping
    public ResponseEntity<yuri.filgueira.yufoodapi.entities.OrderItem> createOrderItem(@PathVariable("orderId") Long orderId, @RequestBody yuri.filgueira.yufoodapi.entities.OrderItem orderItem) {
        return orderItemServices.create(orderId, orderItem);
    }

    @PutMapping
    public ResponseEntity<yuri.filgueira.yufoodapi.entities.OrderItem> updateOrderItem(@PathVariable("orderId") Long orderId, @RequestBody yuri.filgueira.yufoodapi.entities.OrderItem orderItem) {
        return orderItemServices.update(orderId, orderItem);
    }

    @DeleteMapping(value = "/{orderItemId}")
    public ResponseEntity<Void> delete(@PathVariable("orderId") Long orderId, @PathVariable Long  orderItemId) {
        return orderItemServices.delete(orderId, orderItemId);
    }

}
