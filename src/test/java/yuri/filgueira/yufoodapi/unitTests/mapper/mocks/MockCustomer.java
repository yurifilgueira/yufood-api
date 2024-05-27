package yuri.filgueira.yufoodapi.unitTests.mapper.mocks;

import yuri.filgueira.yufoodapi.entities.*;

import java.util.HashSet;
import java.util.Set;

public class MockCustomer {

    public static MockCustomer getMockCustomer(){
        return MockCustomerHolder.INSTANCE;
    }

    private final MockAddress mockAddress = MockAddress.getMockAddress();
    private final MockOrder mockOrder = MockOrder.getMockOrder();
    private final MockFood mockFood = MockFood.getMockFood();

    public Customer mockEntity(long id) {
        Customer entity = new Customer();

        entity.setId(id);

        Set<Address> addresses = new HashSet<>();
        addresses.add(mockAddress.mockEntity(id + 2));
        addresses.add(mockAddress.mockEntity(id + 3));

        entity.setAddresses(addresses);
        entity.setCpf("12345678910");
        entity.setName("The Customer");
        entity.setEmail("therestaurant@email.com");

        Set<Order> orders = new HashSet<>();
        orders.add(mockOrder.mockEntity(id + 4));

        entity.setOrders(orders);

        return entity;
    }

    public Customer mockEntityWithoutOrders(long id) {
        Customer entity = new Customer();

        entity.setId(id);

        Set<Address> addresses = new HashSet<>();
        addresses.add(mockAddress.mockEntity(id + 2));
        addresses.add(mockAddress.mockEntity(id + 3));

        entity.setAddresses(addresses);
        entity.setCpf("12345678910");
        entity.setName("The Customer");
        entity.setEmail("therestaurant@email.com");

        return entity;
    }

    private static class MockCustomerHolder {
        public final static MockCustomer INSTANCE = new MockCustomer();
    }

}
