package yuri.filgueira.yufoodapi.unitTests.mapper;

import org.junit.jupiter.api.Test;
import yuri.filgueira.yufoodapi.data.vo.OrderVO;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.unitTests.mapper.mocks.MockOrder;

import static org.junit.jupiter.api.Assertions.*;

public class OrderModelMapperConverterTest {

    private final MockOrder input = new MockOrder();
    private final MyModelMapper mapper = new MyModelMapper();

    @Test
    public void parseEntityToVOBasicFieldsTest() {
        long id = 1L;
        var order = input.mockEntity(id);

        OrderVO vo = mapper.convertValue(order, OrderVO.class);

        assertEquals(order.getId(), vo.getKey());
        assertEquals(order.getTotal(), vo.getTotal());

        assertNotNull(order.getOrderItems());
        assertNotNull(vo.getOrderItems());
        assertEquals(order.getOrderItems().size(), vo.getOrderItems().size());
    }

    @Test
    public void parseEntityToVORestaurantFieldsTest() {
        long id = 1L;
        var order = input.mockEntity(id);
        OrderVO vo = mapper.convertValue(order, OrderVO.class);

        assertNotNull(order.getRestaurant());

        assertEquals(order.getRestaurant().getId(), vo.getRestaurant().getKey());
        assertEquals(order.getRestaurant().getName(), vo.getRestaurant().getName());
        assertEquals(order.getRestaurant().getCnpj(), vo.getRestaurant().getCnpj());

        assertNotNull(order.getRestaurant().getFoods());
        assertNotNull(vo.getRestaurant().getFoods());

        assertNotNull(order.getRestaurant().getFoods());
        assertNotNull(vo.getRestaurant().getFoods());
        assertEquals(order.getRestaurant().getFoods().size(), vo.getRestaurant().getFoods().size());

        assertNotNull(order.getRestaurant().getOrders());
        assertNotNull(vo.getRestaurant().getOrders());
        assertEquals(order.getRestaurant().getOrders().size(), vo.getRestaurant().getOrders().size());

        assertNotNull(order.getRestaurant().getAddresses());
        assertNotNull(vo.getRestaurant().getAddresses());
        assertEquals(order.getRestaurant().getAddresses().size(), vo.getRestaurant().getAddresses().size());

        assertEquals(order.getRestaurant().getCnpj(), vo.getRestaurant().getCnpj());
        assertEquals(order.getRestaurant().getName(), vo.getRestaurant().getName());
        assertEquals(order.getRestaurant().getEmail(), vo.getRestaurant().getEmail());
    }

    @Test
    public void parseEntityToVOCustomerFieldsTest() {
        long id = 1L;
        var order = input.mockEntity(id);

        OrderVO vo = mapper.convertValue(order, OrderVO.class);

        assertEquals(order.getCustomer().getId(), vo.getCustomer().getKey());
        assertEquals(order.getCustomer().getName(), vo.getCustomer().getName());
        assertEquals(order.getCustomer().getEmail(), vo.getCustomer().getEmail());

        assertNotNull(order.getCustomer().getOrders());
        assertNotNull(vo.getCustomer().getOrders());
        assertEquals(order.getCustomer().getOrders().size(), vo.getCustomer().getOrders().size());

        assertNotNull(order.getCustomer().getAddresses());
        assertNotNull(vo.getCustomer().getAddresses());
        assertEquals(order.getCustomer().getAddresses().size(), vo.getCustomer().getAddresses().size());
    }
}
