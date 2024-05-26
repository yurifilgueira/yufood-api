package yuri.filgueira.yufoodapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.entities.OrderItem;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.repositories.OrderItemRepository;
import yuri.filgueira.yufoodapi.repositories.OrderRepository;
import yuri.filgueira.yufoodapi.data.vo.OrderItemVO;

import java.util.List;

@Service
public class OrderItemServices {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemServices.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MyModelMapper mapper;

    public ResponseEntity<List<OrderItem>> findAll(Long orderId){
        
        var order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Resource not found"));
        
        List<OrderItem> items = order.getOrderItems().stream().toList();
        if(items.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(items);
    }

    public ResponseEntity<OrderItemVO> findById(Long orderId, Long orderItemId){

        var orderItem = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Resource not found"))
                .getOrderItems().stream().filter(item -> item.getId().equals(orderItemId)).findFirst().orElseThrow(() -> new RuntimeException("Resource not found"));

        var orderItemVO = mapper.convertValue(orderItem, OrderItemVO.class);

        return ResponseEntity.ok(orderItemVO);
    }

    public ResponseEntity<OrderItem> create(Long orderId, OrderItem item){

        logger.info("Finding order");
        var order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Resource not found"));

        order.getOrderItems().add(item);
        orderRepository.save(order);

        var orderItem = orderItemRepository.save(item);
        orderItemRepository.save(orderItem);

        return ResponseEntity.ok(orderItem);
    }

    public ResponseEntity<OrderItem> update(Long orderId, OrderItem orderItem){

        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        var entity = orderItemRepository.findById(orderItem.getId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        entity.setFood(orderItem.getFood());
        entity.setQuantity(orderItem.getQuantity());
        entity.setSubtotal(orderItem.getSubtotal());

        order.getOrderItems().add(entity);
        orderRepository.save(order);

        return ResponseEntity.ok(orderItemRepository.save(entity));

    }

    public ResponseEntity<Void> delete(Long orderId, Long orderItemId){

        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        var entity = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        order.getOrderItems().remove(entity);
        orderRepository.save(order);

        orderItemRepository.deleteById(orderItemId);

        return ResponseEntity.noContent().build();
    }
}
