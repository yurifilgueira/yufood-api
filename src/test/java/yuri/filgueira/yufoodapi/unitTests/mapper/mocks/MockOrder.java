package yuri.filgueira.yufoodapi.unitTests.mapper.mocks;

import yuri.filgueira.yufoodapi.entities.Order;
import yuri.filgueira.yufoodapi.entities.OrderItem;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class MockOrder {

    public static MockOrder getMockOrder(){
        return MockOrderHolder.INSTANCE;
    }

    private final MockOrderItem mockOrderItem = MockOrderItem.getMockOrderItem();
    private final MockCustomer mockCustomer = MockCustomer.getMockCustomer();
    private final MockRestaurant mockRestaurant = MockRestaurant.getMockRestaurant();

    public Order mockEntity(long id){
        Order entity = new Order();

        entity.setId(id);

        Set<OrderItem> items = new HashSet<>();
        items.add(mockOrderItem.mockEntity(id + 1));
        items.add(mockOrderItem.mockEntity(id + 2));
        entity.setOrderItems(items);

        BigDecimal total = BigDecimal.valueOf(items.stream().map(OrderItem::getSubtotal).mapToDouble(BigDecimal::doubleValue).sum());

        entity.setTotal(total);

        entity.setRestaurant(mockRestaurant.mockEntityWithoutOrders(id + 4));

        Set<Order> orders = new HashSet<>();
        orders.add(entity);

        entity.getRestaurant().setOrders(orders);
        entity.setCustomer(mockCustomer.mockEntityWithoutOrders(id + 4));
        entity.getCustomer().setOrders(orders);

        return entity;
    }

    private static class MockOrderHolder {
        public static final MockOrder INSTANCE = new MockOrder();
    }

}
