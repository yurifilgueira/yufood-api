package yuri.filgueira.yufoodapi.unitTests.mapper;

import org.junit.jupiter.api.Test;
import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.data.vo.OrderItemVO;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.entities.OrderItem;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.unitTests.mapper.mocks.MockOrderItem;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderItemModelMapperConverterTest {

    private final MockOrderItem input = new MockOrderItem();
    private final MyModelMapper mapper = new MyModelMapper();

    @Test
    public void parseEntityToVoTest(){

        var entity = input.mockEntity(1L);
        var output = mapper.convertValue(entity, OrderItemVO.class);

        assertNotNull(output);

        assertEquals(1L, output.getKey());

        assertEquals(FoodVO.class.getName(), output.getFood().getClass().getName());
        assertNotNull(output.getFood());
        assertEquals(2L, output.getFood().getKey());
        assertEquals("Prato do dia", output.getFood().getName());
        assertEquals(new BigDecimal("23.90"), output.getFood().getPrice());

        assertEquals(3, output.getQuantity());
        assertEquals(new BigDecimal("71.70"), output.getSubtotal());

    }

    @Test
    public void parseVoToEntityTest(){

        var entity = input.mockVO(1L);
        var output = mapper.convertValue(entity, OrderItem.class);

        assertNotNull(output);

        assertEquals(1L, output.getId());

        assertEquals(Food.class.getName(), output.getFood().getClass().getName());
        assertNotNull(output.getFood());
        assertEquals(2L, output.getFood().getId());
        assertEquals("Prato do dia", output.getFood().getName());
        assertEquals(new BigDecimal("23.90"), output.getFood().getPrice());

        assertEquals(3, output.getQuantity());
        assertEquals(new BigDecimal("71.70"), output.getSubtotal());
    }
}
