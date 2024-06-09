package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.data.vo.OrderVO;
import yuri.filgueira.yufoodapi.services.OrderServices;

import java.util.List;


@RestController
@RequestMapping(value = "v1/api/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping
    public ResponseEntity<List<OrderVO>> findAll(Long orderId) {
        return orderServices.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderVO> findById(@PathVariable("id") Long id) {
        return orderServices.findById(id);
    }

    @PostMapping
    public ResponseEntity<OrderVO> create(@RequestBody OrderVO orderVO) {
        return this.orderServices.create(orderVO);
    }

    @PutMapping
    public ResponseEntity<OrderVO> update(@RequestBody OrderVO orderVO) {
        return this.orderServices.update(orderVO);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        return orderServices.delete(id);
    }
}
