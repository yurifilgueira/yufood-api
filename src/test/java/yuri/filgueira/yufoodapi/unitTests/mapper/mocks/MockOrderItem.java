package yuri.filgueira.yufoodapi.unitTests.mapper.mocks;


import yuri.filgueira.yufoodapi.data.vo.OrderItemVO;
import yuri.filgueira.yufoodapi.entities.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockOrderItem {

    private MockFood mockFood = new MockFood();

    public OrderItem mockEntity(long key) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(key);
        orderItem.setFood(mockFood.mockEntity(key + 1));
        orderItem.setQuantity(3);

        orderItem.setSubtotal(new BigDecimal("71.70"));

        return orderItem;
    }

    public OrderItemVO mockVO(long key) {
        OrderItemVO vo = new OrderItemVO();
        vo.setKey(key);
        vo.setFood(mockFood.mockVO(key + 1));
        vo.setQuantity(3);

        vo.setSubtotal(new BigDecimal("71.70"));

        return vo;
    }

    public List<OrderItem> mockEntityList() {
        List<OrderItem> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            OrderItem entity = mockEntity(i);

            entity.setFood(mockFood.mockEntity((i + 1)));
            entity.getFood().setName(("Name: " + (i + 1)));
            entity.getFood().setPrice(new BigDecimal(20 + i));

            entity.setQuantity(1 + i);
            entity.setSubtotal(getSubtotal(entity));

            entities.add(entity);
        }

        return entities;

    }


    public List<OrderItemVO> mockVoList() {
        List<OrderItemVO> vos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            OrderItemVO vo = mockVO(i);

            vo.setFood(mockFood.mockVO((i + 1)));
            vo.getFood().setName(("Name: " + (i + 1)));
            vo.getFood().setPrice(new BigDecimal(20 + i));

            vo.setQuantity(1 + i);
            vo.setSubtotal(getSubtotal(vo));

            vos.add(vo);
        }

        return vos;

    }

    public <T> BigDecimal getSubtotal(T item) {

        BigDecimal price;
        BigDecimal subtotal;
        if (item instanceof OrderItem) {
             price = ((OrderItem) item).getFood().getPrice();
             int quantity = ((OrderItem) item).getQuantity();
             subtotal = price.multiply(new BigDecimal(quantity));
        }
        else {
            price = ((OrderItemVO)item).getFood().getPrice();
            int quantity = ((OrderItemVO)item).getQuantity();
            subtotal = price.multiply(new BigDecimal(quantity));
        }

        return subtotal;
    }
}
