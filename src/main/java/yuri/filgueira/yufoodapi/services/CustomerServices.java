package yuri.filgueira.yufoodapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.data.vo.CustomerVO;
import yuri.filgueira.yufoodapi.entities.Customer;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.CustomerRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository repository;
   @Autowired
    private MyModelMapper mapper;

    private Logger logger = Logger.getLogger(CustomerServices.class.getName());

    public ResponseEntity<List<CustomerVO>> findAll(){
        List<CustomerVO> items = mapper.convertList(repository.findAll(), CustomerVO.class);
        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<CustomerVO> findById(Long id){
        var customer = repository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));

        return ResponseEntity.ok(mapper.convertValue(customer, CustomerVO.class));

    }

    public ResponseEntity<CustomerVO> create(CustomerVO customerVO){

        logger.info("Creating customer...");

        var entity = mapper.convertValue(customerVO, Customer.class);

        return ResponseEntity.ok(mapper.convertValue(repository.save(entity), CustomerVO.class));
    }

    public ResponseEntity<CustomerVO> update(CustomerVO customerVO){

        var customer = mapper.convertValue(customerVO, Customer.class);
        var entity = repository.findById(customer.getId()).orElseThrow(()-> new RuntimeException("Resource not found"));

        entity.setEmail(customer.getEmail());
        entity.setName(customer.getName());
        entity.setCpf(customer.getCpf());
        entity.setOrders(customer.getOrders());
        entity.setAddresses(customer.getAddresses());

        return ResponseEntity.ok(mapper.convertValue(repository.save(entity), CustomerVO.class));

    }

    public ResponseEntity<Void> delete(Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
