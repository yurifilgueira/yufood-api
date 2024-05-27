package yuri.filgueira.yufoodapi.unitTests.mapper.mocks;

import yuri.filgueira.yufoodapi.data.vo.AddressVO;
import yuri.filgueira.yufoodapi.entities.Address;

import java.util.ArrayList;
import java.util.List;

public class MockAddress {

    public static MockAddress getMockAddress(){
        return MockAddressHolder.INSTANCE;
    }

    public Address mockEntity(long id) {
        Address entity = new Address();

        entity.setId(id);
        entity.setZipCode("12345-678");
        entity.setCity("New City");
        entity.setState("ST");
        entity.setNumber("1234");
        entity.setStreet("New Street");
        entity.setComplement("The Complement");
        entity.setNeighborhood("Other Neighborhood");

        return entity;
    }

    public AddressVO mockVO(long key) {
        AddressVO vo = new AddressVO();

        vo.setKey(key);
        vo.setZipCode("12345-678");
        vo.setCity("New City");
        vo.setState("ST");
        vo.setNumber("1234");
        vo.setStreet("New Street");
        vo.setComplement("The Complement");
        vo.setNeighborhood("Other Neighborhood");

        return vo;
    }

    public List<Address> mockEntityList() {
        List<Address> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Address entity = mockEntity(i);

            entity.setId((long) i);
            entity.setZipCode("ZipCode: " + i);
            entity.setCity("New City: " + i);
            entity.setState("ST: " + i);
            entity.setNumber(String.valueOf(1000 + i));
            entity.setStreet("New Street: " + i);
            entity.setComplement("The Complement: " + i);
            entity.setNeighborhood("Other Neighborhood: " + i);

            entities.add(entity);
        }

        return entities;

    }

    public List<AddressVO> mockVOList() {
        List<AddressVO> vos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            AddressVO vo = mockVO(i);

            vo.setKey((long) i);
            vo.setZipCode("ZipCode: " + i);
            vo.setCity("New City: " + i);
            vo.setState("ST: " + i);
            vo.setNumber(String.valueOf(1000 + i));
            vo.setStreet("New Street: " + i);
            vo.setComplement("The Complement: " + i);
            vo.setNeighborhood("Other Neighborhood: " + i);

            vos.add(vo);
        }

        return vos;
    }
    private static class MockAddressHolder {
        public final static MockAddress INSTANCE = new MockAddress();
    }
}
