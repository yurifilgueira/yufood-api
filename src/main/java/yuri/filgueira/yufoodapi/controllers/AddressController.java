package yuri.filgueira.yufoodapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuri.filgueira.yufoodapi.data.vo.AddressVO;
import yuri.filgueira.yufoodapi.services.AddressServices;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/addresses")
public class AddressController {

    @Autowired
    private AddressServices services;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<AddressVO>> findAll(@PathVariable("userId") Long userId) {
        return services.findAll(userId);
    }

    @GetMapping(value = "/{userId}/{addressId}")
    public ResponseEntity<AddressVO> findById(@PathVariable("userId") Long userId, @PathVariable("addressId") Long addressId) {
        return services.findById(userId, addressId);
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<AddressVO> create(@PathVariable("userId") Long userId, @RequestBody AddressVO addressVO) {
        return services.create(userId, addressVO);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<AddressVO> update(@PathVariable("userId") Long userId, @RequestBody AddressVO addressVO) {
        return services.update(userId, addressVO);
    }

    @DeleteMapping(value = "/{userId}/{addressId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("addressId") Long addressId) {
        return services.delete(userId, addressId);
    }

}
