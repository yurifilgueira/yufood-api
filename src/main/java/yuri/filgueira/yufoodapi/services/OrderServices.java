package yuri.filgueira.yufoodapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.entities.Order;
import yuri.filgueira.yufoodapi.repositories.CustomerRepository;
import yuri.filgueira.yufoodapi.repositories.OrderItemRepository;
import yuri.filgueira.yufoodapi.repositories.OrderRepository;
import yuri.filgueira.yufoodapi.repositories.RestaurantRepository;

import java.util.List;

@Service
public class OrderServices {

    private static final Logger logger = LoggerFactory.getLogger(OrderServices.class);
    @Autowired
    private OrderRepository repository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

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

        var customer = customerRepository.findById(item.getCustomer().getId())
                .orElseThrow(()-> new RuntimeException("Resource not found"));
        var restaurant = restaurantRepository.findById(item.getRestaurant().getId())
                .orElseThrow(()-> new RuntimeException("Resource not found"));

        item.setCustomer(customer);
        item.setRestaurant(restaurant);

        customer.addOrder(item);
        customerRepository.save(customer);

        restaurant.addOrder(item);

        restaurantRepository.save(restaurant);

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

        var order = repository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));

        var restaurant = order.getRestaurant();
        restaurant.removeOrder(order);
        restaurantRepository.save(restaurant);

        var customer = order.getCustomer();
        customer.removeOrder(order);

        customerRepository.save(customer);

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
