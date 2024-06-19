package yuri.filgueira.yufoodapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.controllers.CustomerController;
import yuri.filgueira.yufoodapi.controllers.OrderController;
import yuri.filgueira.yufoodapi.controllers.RestaurantController;
import yuri.filgueira.yufoodapi.data.vo.OrderVO;
import yuri.filgueira.yufoodapi.entities.Order;
import yuri.filgueira.yufoodapi.exceptions.ResourceNotFoundException;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.CustomerRepository;
import yuri.filgueira.yufoodapi.repositories.OrderItemRepository;
import yuri.filgueira.yufoodapi.repositories.OrderRepository;
import yuri.filgueira.yufoodapi.repositories.RestaurantRepository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    private MyModelMapper mapper;

    public ResponseEntity<List<OrderVO>> findAll(){
        List<OrderVO> orderVOs = mapper.convertList(repository.findAll(), OrderVO.class);
        if(orderVOs.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        orderVOs.forEach(orderVO -> {
            orderVO.add(linkTo(methodOn(OrderController.class)
                    .findById(orderVO.getKey())).withSelfRel());
            orderVO.add(linkTo(methodOn(CustomerController.class)
                    .findById(orderVO.getCustomer().getKey())).withRel("Customer"));
            orderVO.add(linkTo(methodOn(RestaurantController.class)
                    .findById(orderVO.getRestaurant().getKey())).withRel("Restaurant"));
        });

        return ResponseEntity.ok(orderVOs);
    }

    public ResponseEntity<OrderVO> findById(Long orderId){
        var order = repository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

        var orderVO = mapper.convertValue(order, OrderVO.class);

        orderVO.add(linkTo(methodOn(OrderController.class)
                .findAll()).withRel("All Orders"));
        orderVO.add(linkTo(methodOn(CustomerController.class)
                .findById(orderVO.getCustomer().getKey())).withRel("Customer"));
        orderVO.add(linkTo(methodOn(RestaurantController.class)
                .findById(orderVO.getRestaurant().getKey())).withRel("Restaurant"));

        return ResponseEntity.ok(orderVO);
    }

    public ResponseEntity<OrderVO> create(OrderVO orderVO){

        var order = mapper.convertValue(orderVO, Order.class);
        var customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Resource not found"));
        var restaurant = restaurantRepository.findById(order.getRestaurant().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

        order.setCustomer(customer);
        order.setRestaurant(restaurant);

        customer.addOrder(order);
        customerRepository.save(customer);

        restaurant.addOrder(order);

        restaurantRepository.save(restaurant);

        orderVO.add(linkTo(methodOn(OrderController.class)
                .findById(orderVO.getKey())).withSelfRel());
        orderVO.add(linkTo(methodOn(CustomerController.class)
                .findById(orderVO.getCustomer().getKey())).withRel("Customer"));
        orderVO.add(linkTo(methodOn(RestaurantController.class)
                .findById(orderVO.getRestaurant().getKey())).withRel("Restaurant"));

        return ResponseEntity.ok(mapper.convertValue(order, OrderVO.class));
    }

    public ResponseEntity<OrderVO> update(OrderVO orderVO){

        var order = mapper.convertValue(orderVO, Order.class);
        var entity = repository.findById(order.getId()).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

        entity.setRestaurant(order.getRestaurant());
        entity.setCustomer(order.getCustomer());
        entity.setOrderItems(order.getOrderItems());
        entity.setTotal(order.getTotal());

        var vo = mapper.convertValue(repository.save(entity), OrderVO.class);

        vo.add(linkTo(methodOn(OrderController.class)
                .findById(orderVO.getKey())).withSelfRel());
        vo.add(linkTo(methodOn(CustomerController.class)
                .findById(orderVO.getCustomer().getKey())).withRel("Customer"));
        vo.add(linkTo(methodOn(RestaurantController.class)
                .findById(orderVO.getRestaurant().getKey())).withRel("Restaurant"));

        return ResponseEntity.ok(vo);

    }

    public ResponseEntity<Void> delete(Long id){

        var order = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

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
