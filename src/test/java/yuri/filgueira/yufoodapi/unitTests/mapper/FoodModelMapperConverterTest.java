package yuri.filgueira.yufoodapi.unitTests.mapper;

import org.junit.jupiter.api.Test;
import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.unitTests.mapper.mocks.MockFood;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FoodModelMapperConverterTest {

    private final MockFood input = new MockFood();
    private final MyModelMapper mapper = new MyModelMapper();

    @Test
    public void parseEntityToVoTest(){

        var entity = input.mockEntity(1L);
        var output = mapper.convertValue(entity, FoodVO.class);

        assertNotNull(output);

        assertEquals(1L, output.getKey());
        assertEquals("Prato do dia", output.getName());
        assertEquals(1L, output.getKey());
        assertEquals(new BigDecimal("23.90"), output.getPrice());
    }

    @Test
    public void parseVoToEntityTest(){

        var vo = input.mockVO(1L);
        var output = mapper.convertValue(vo, Food.class);

        assertNotNull(output);

        assertEquals(1L, output.getId());
        assertEquals("Prato do dia", output.getName());
        assertEquals(1L, output.getId());
        assertEquals(new BigDecimal("23.90"), output.getPrice());
    }
}
