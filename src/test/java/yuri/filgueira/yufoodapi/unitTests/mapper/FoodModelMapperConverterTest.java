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
        assertEquals(new BigDecimal("23.90"), output.getPrice());
    }

    @Test
    public void parseVoToEntityTest(){

        var vo = input.mockVO(1L);
        var output = mapper.convertValue(vo, Food.class);

        assertNotNull(output);

        assertEquals(1L, output.getId());
        assertEquals("Prato do dia", output.getName());
        assertEquals(new BigDecimal("23.90"), output.getPrice());
    }

    @Test
    public void parseEntityListToVoList(){
        var entities = input.mockEntityList();

        var output = mapper.convertList(entities, FoodVO.class);

        assertNotNull(output);
        assertNotNull(output.get(2));
        assertNotNull(output.get(6));
        assertNotNull(output.get(9));

        assertEquals(2L, output.get(2).getKey());
        assertEquals("Name: 2", output.get(2).getName());
        assertEquals(new BigDecimal("22"), output.get(2).getPrice());

        assertEquals(6L, output.get(6).getKey());
        assertEquals("Name: 6", output.get(6).getName());
        assertEquals(new BigDecimal("26"), output.get(6).getPrice());

        assertEquals(9L, output.get(9).getKey());
        assertEquals("Name: 9", output.get(9).getName());
        assertEquals(new BigDecimal("29"), output.get(9).getPrice());

    }

    @Test
    public void parseVoListToEntityList(){
        var vos = input.mockVOList();

        var output = mapper.convertList(vos, Food.class);

        assertNotNull(output);
        assertNotNull(output.get(2));
        assertNotNull(output.get(6));
        assertNotNull(output.get(9));

        assertEquals(2L, output.get(2).getId());
        assertEquals("Name: 2", output.get(2).getName());
        assertEquals(new BigDecimal("22"), output.get(2).getPrice());

        assertEquals(6L, output.get(6).getId());
        assertEquals("Name: 6", output.get(6).getName());
        assertEquals(new BigDecimal("26"), output.get(6).getPrice());

        assertEquals(9L, output.get(9).getId());
        assertEquals("Name: 9", output.get(9).getName());
        assertEquals(new BigDecimal("29"), output.get(9).getPrice());
    }

}
