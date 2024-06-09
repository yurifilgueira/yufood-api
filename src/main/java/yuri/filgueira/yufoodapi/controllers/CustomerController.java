package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.data.vo.CustomerVO;
import yuri.filgueira.yufoodapi.services.CustomerServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServices services;

    @GetMapping
    public ResponseEntity<List<CustomerVO>> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerVO> findById(@PathVariable Long id) {
        return services.findById(id);
    }

    @PostMapping
    public ResponseEntity<CustomerVO> create(@RequestBody CustomerVO customerVO) {
        return services.create(customerVO);
    }

    @PutMapping
    public ResponseEntity<CustomerVO> update(@RequestBody CustomerVO customerVO) {
        return services.update(customerVO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return services.delete(id);
    }
}
