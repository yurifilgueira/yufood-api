package yuri.filgueira.yufoodapi.unitTests.mapper;

import org.junit.jupiter.api.Test;
import yuri.filgueira.yufoodapi.data.vo.AddressVO;
import yuri.filgueira.yufoodapi.entities.Address;
import yuri.filgueira.yufoodapi.mapper.modelMapper.MyModelMapper;
import yuri.filgueira.yufoodapi.unitTests.mapper.mocks.MockAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddressModelMapperConverterTest {

    private final MockAddress input = new MockAddress();
    private final MyModelMapper mapper = new MyModelMapper();

    @Test
    public void parseEntityToVoTest(){
        var entity = input.mockEntity(1L);
        var output = mapper.convertValue(entity, AddressVO.class);

        assertNotNull(output);

        assertEquals(1L, output.getKey());
        assertEquals("12345-678", output.getZipCode());
        assertEquals("New City", output.getCity());
        assertEquals("ST", output.getState());
        assertEquals("1234", output.getNumber());
        assertEquals("New Street", output.getStreet());
        assertEquals("The Complement", output.getComplement());
        assertEquals("Other Neighborhood", output.getNeighborhood());
    }

    @Test
    public void parseVoToEntityTest(){
        var vo = input.mockVO(1L);
        var output = mapper.convertValue(vo, Address.class);

        assertNotNull(output);

        assertEquals(1L, output.getId());
        assertEquals("12345-678", output.getZipCode());
        assertEquals("New City", output.getCity());
        assertEquals("ST", output.getState());
        assertEquals("1234", output.getNumber());
        assertEquals("New Street", output.getStreet());
        assertEquals("The Complement", output.getComplement());
        assertEquals("Other Neighborhood", output.getNeighborhood());
    }

    @Test
    public void parseEntityListToVoList(){
        var entities = input.mockEntityList();
        var output = mapper.convertList(entities, AddressVO.class);

        assertNotNull(output);
        assertNotNull(output.get(2));
        assertNotNull(output.get(6));
        assertNotNull(output.get(9));

        assertEquals(2L, output.get(2).getKey());
        assertEquals("ZipCode: 2", output.get(2).getZipCode());
        assertEquals("New City: 2", output.get(2).getCity());
        assertEquals("ST: 2", output.get(2).getState());
        assertEquals("1002", output.get(2).getNumber());
        assertEquals("New Street: 2", output.get(2).getStreet());
        assertEquals("The Complement: 2", output.get(2).getComplement());
        assertEquals("Other Neighborhood: 2", output.get(2).getNeighborhood());

        assertEquals(6L, output.get(6).getKey());
        assertEquals("ZipCode: 6", output.get(6).getZipCode());
        assertEquals("New City: 6", output.get(6).getCity());
        assertEquals("ST: 6", output.get(6).getState());
        assertEquals("1006", output.get(6).getNumber());
        assertEquals("New Street: 6", output.get(6).getStreet());
        assertEquals("The Complement: 6", output.get(6).getComplement());
        assertEquals("Other Neighborhood: 6", output.get(6).getNeighborhood());

        assertEquals(9L, output.get(9).getKey());
        assertEquals("ZipCode: 9", output.get(9).getZipCode());
        assertEquals("New City: 9", output.get(9).getCity());
        assertEquals("ST: 9", output.get(9).getState());
        assertEquals("1009", output.get(9).getNumber());
        assertEquals("New Street: 9", output.get(9).getStreet());
        assertEquals("The Complement: 9", output.get(9).getComplement());
        assertEquals("Other Neighborhood: 9", output.get(9).getNeighborhood());

    }

    @Test
    public void parseVoListToEntityList(){
        var vos = input.mockVOList();
        var output = mapper.convertList(vos, Address.class);

        assertNotNull(output);
        assertNotNull(output.get(2));
        assertNotNull(output.get(6));
        assertNotNull(output.get(9));

        assertEquals(2L, output.get(2).getId());
        assertEquals("ZipCode: 2", output.get(2).getZipCode());
        assertEquals("New City: 2", output.get(2).getCity());
        assertEquals("ST: 2", output.get(2).getState());
        assertEquals("1002", output.get(2).getNumber());
        assertEquals("New Street: 2", output.get(2).getStreet());
        assertEquals("The Complement: 2", output.get(2).getComplement());
        assertEquals("Other Neighborhood: 2", output.get(2).getNeighborhood());

        assertEquals(6L, output.get(6).getId());
        assertEquals("ZipCode: 6", output.get(6).getZipCode());
        assertEquals("New City: 6", output.get(6).getCity());
        assertEquals("ST: 6", output.get(6).getState());
        assertEquals("1006", output.get(6).getNumber());
        assertEquals("New Street: 6", output.get(6).getStreet());
        assertEquals("The Complement: 6", output.get(6).getComplement());
        assertEquals("Other Neighborhood: 6", output.get(6).getNeighborhood());

        assertEquals(9L, output.get(9).getId());
        assertEquals("ZipCode: 9", output.get(9).getZipCode());
        assertEquals("New City: 9", output.get(9).getCity());
        assertEquals("ST: 9", output.get(9).getState());
        assertEquals("1009", output.get(9).getNumber());
        assertEquals("New Street: 9", output.get(9).getStreet());
        assertEquals("The Complement: 9", output.get(9).getComplement());
        assertEquals("Other Neighborhood: 9", output.get(9).getNeighborhood());
    }
}
