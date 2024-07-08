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
import yuri.filgueira.yufoodapi.entities.Customer;
import yuri.filgueira.yufoodapi.entities.Order;
import yuri.filgueira.yufoodapi.entities.Restaurant;
import yuri.filgueira.yufoodapi.exceptions.ResourceNotFoundException;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderServices {

    private static final Logger logger = LoggerFactory.getLogger(OrderServices.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MyModelMapper mapper;

    public ResponseEntity<List<OrderVO>> findAll(long userId){

        //TODO

        /*
        List<OrderVO> orderVOs = mapper.convertList(, OrderVO.class);
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
         */

        return ResponseEntity.ok(new ArrayList<>());
    }

    public ResponseEntity<OrderVO> findById(long orderId){
        var order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

        var orderVO = mapper.convertValue(order, OrderVO.class);
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
        var entity = orderRepository.findById(order.getId()).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

        entity.setRestaurant(order.getRestaurant());
        entity.setCustomer(order.getCustomer());
        entity.setOrderItems(order.getOrderItems());
        entity.setTotal(order.getTotal());

        var vo = mapper.convertValue(orderRepository.save(entity), OrderVO.class);

        vo.add(linkTo(methodOn(OrderController.class)
                .findById(orderVO.getKey())).withSelfRel());
        vo.add(linkTo(methodOn(CustomerController.class)
                .findById(orderVO.getCustomer().getKey())).withRel("Customer"));
        vo.add(linkTo(methodOn(RestaurantController.class)
                .findById(orderVO.getRestaurant().getKey())).withRel("Restaurant"));

        return ResponseEntity.ok(vo);

    }

    public ResponseEntity<Void> delete(Long id){

        var order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));

        var restaurant = order.getRestaurant();
        restaurant.removeOrder(order);
        restaurantRepository.save(restaurant);

        var customer = order.getCustomer();
        customer.removeOrder(order);

        customerRepository.save(customer);

        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
