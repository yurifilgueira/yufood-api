package yuri.filgueira.yufoodapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.data.vo.AddressVO;
import yuri.filgueira.yufoodapi.entities.Address;
import yuri.filgueira.yufoodapi.entities.EntityObject;
import yuri.filgueira.yufoodapi.exceptions.ResourceNotFoundException;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.AddressRepository;
import yuri.filgueira.yufoodapi.repositories.EntityObjectRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class AddressServices {

    @Autowired
    private AddressRepository addressRepository;
    private EntityObjectRepository entityObjectRepository;
    @Autowired
    private MyModelMapper mapper;

    public ResponseEntity<List<AddressVO>> findAll(Long userId){

        var user = entityObjectRepository.getReferenceById(userId);

        List<Address> addresses = user.getAddresses().stream().toList();
        if(addresses.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.convertList(addresses, AddressVO.class));
    }

    public ResponseEntity<AddressVO> findById(Long userId, Long addressId){
        var address = addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));
        return ResponseEntity.ok(mapper.convertValue(address, AddressVO.class));
    }

    public ResponseEntity<AddressVO> create(Long userId, AddressVO addressVO){
        var entity = mapper.convertValue(addressVO, Address.class);

        return ResponseEntity.ok(mapper.convertValue(addressRepository.save(entity), AddressVO.class));
    }

    public ResponseEntity<AddressVO> update(Long userId, AddressVO addressVO){

        var address = mapper.convertValue(addressVO, Address.class);
        var entity = addressRepository.findById(address.getId()).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setStreet(address.getStreet());
        entity.setNeighborhood(address.getNeighborhood());
        entity.setZipCode(address.getZipCode());
        entity.setNumber(address.getNumber());
        entity.setComplement(address.getComplement());

        return ResponseEntity.ok(mapper.convertValue(addressRepository.save(entity), AddressVO.class));

    }

    public ResponseEntity<Void> delete(Long userId, Long addressId){
        addressRepository.deleteById(addressId);

        return ResponseEntity.noContent().build();
    }

}
