package yuri.filgueira.yufoodapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.entities.Address;
import yuri.filgueira.yufoodapi.repositories.AddressRepository;

import java.util.List;

@Service
public class AddressServices {

    @Autowired
    private AddressRepository repository;

    public ResponseEntity<List<Address>> findAll(Long userId){
        List<Address> items = repository.findAll();
        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<Address> findById(Long userId, Long addressId){
        var address = repository.findById(userId).orElseThrow(()-> new RuntimeException("Resource not found"));

        return ResponseEntity.ok(address);

    }

    public ResponseEntity<Address> create(Long userId, Address item){
        var address = repository.save(item);

        return ResponseEntity.ok(address);
    }

    public ResponseEntity<Address> update(Long userId, Address address){

        var entity = repository.findById(address.getId()).orElseThrow(()-> new RuntimeException("Resource not found"));

        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setStreet(address.getStreet());
        entity.setNeighborhood(address.getNeighborhood());
        entity.setZipCode(address.getZipCode());
        entity.setNumber(address.getNumber());
        entity.setComplement(address.getComplement());

        return ResponseEntity.ok(repository.save(entity));

    }

    public ResponseEntity<Void> delete(Long userId, Long addressId){
        repository.deleteById(userId);

        return ResponseEntity.noContent().build();
    }

}
