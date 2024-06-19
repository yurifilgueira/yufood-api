package yuri.filgueira.yufoodapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.controllers.OrderItemController;
import yuri.filgueira.yufoodapi.data.vo.OrderVO;
import yuri.filgueira.yufoodapi.entities.OrderItem;
import yuri.filgueira.yufoodapi.exceptions.ResourceNotFoundException;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.OrderItemRepository;
import yuri.filgueira.yufoodapi.repositories.OrderRepository;
import yuri.filgueira.yufoodapi.data.vo.OrderItemVO;

import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderItemServices {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemServices.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MyModelMapper mapper;

    public ResponseEntity<List<OrderItemVO>> findAll(Long orderId){

        var order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var orderItems = order.getOrderItems().stream().toList();

        var orderItemsVO = mapper.convertList(orderItems, OrderItemVO.class);

        if(orderItemsVO.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        orderItemsVO.forEach(orderItem ->
            orderItem.add(linkTo(methodOn(OrderItemController.class).findOrderItemById(orderId, orderItem.getKey())).withSelfRel()
        ));

        return ResponseEntity.ok(orderItemsVO);
    }

    public ResponseEntity<OrderItemVO> findById(Long orderId, Long orderItemId){

        var order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var orderItem = order.getOrderItems().stream()
                .filter(item -> Objects.equals(item.getId(), orderItemId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var orderItemVO = mapper.convertValue(orderItem, OrderItemVO.class);

        orderItemVO.add(linkTo(methodOn(OrderItemController.class).findAllOrderItems(orderId)).withRel("All OrderItems"));

        return ResponseEntity.ok(orderItemVO);
    }

    public ResponseEntity<OrderItemVO> create(Long orderId, OrderItemVO orderItemVO){

        logger.info("Finding order");
        var order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var entity = mapper.convertValue(orderItemVO, OrderItem.class);

        order.getOrderItems().add(entity);
        orderRepository.save(order);

        var orderItem = orderItemRepository.save(entity);

        var vo = mapper.convertValue(orderItem, OrderItemVO.class);
        orderItemVO.add(linkTo(methodOn(OrderItemController.class).findAllOrderItems(orderId)).withRel("All OrderItems"));

        return ResponseEntity.ok(vo);
    }

    public ResponseEntity<OrderItemVO> update(Long orderId, OrderItemVO orderItemVO){

        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var entity = orderItemRepository.findById(orderItemVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var orderItem = mapper.convertValue(orderItemVO, OrderItem.class);
        entity.setFood(orderItem.getFood());
        entity.setQuantity(orderItem.getQuantity());
        entity.setSubtotal(orderItem.getSubtotal());

        order.getOrderItems().add(entity);
        orderRepository.save(order);

        var vo = mapper.convertValue(orderItemRepository.save(entity), OrderItemVO.class);
        orderItemVO.add(linkTo(methodOn(OrderItemController.class).findAllOrderItems(orderId)).withRel("All OrderItems"));

        return ResponseEntity.ok(vo);

    }

    public ResponseEntity<Void> delete(Long orderId, Long orderItemId){

        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        var entity = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        order.getOrderItems().remove(entity);
        orderRepository.save(order);

        orderItemRepository.deleteById(orderItemId);

        return ResponseEntity.noContent().build();
    }
}
