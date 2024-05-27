package yuri.filgueira.yufoodapi.unitTests.mapper.mocks;

import yuri.filgueira.yufoodapi.entities.Address;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.entities.Order;
import yuri.filgueira.yufoodapi.entities.Restaurant;

import java.util.HashSet;
import java.util.Set;

public class MockRestaurant {

    public static MockRestaurant getMockRestaurant(){
        return MockRestaurantHolder.INSTANCE;
    }

    private final MockAddress mockAddress = MockAddress.getMockAddress();
    private final MockOrder mockOrder = MockOrder.getMockOrder();
    private final MockFood mockFood = MockFood.getMockFood();

    public Restaurant mockEntity(long id) {

        Restaurant entity = new Restaurant();

        entity.setId(id);

        Set<Address> addresses = new HashSet<>();
        addresses.add(mockAddress.mockEntity(id + 2));
        addresses.add(mockAddress.mockEntity(id + 3));

        entity.setAddresses(addresses);
        entity.setCnpj("123456789011");
        entity.setName("The Restaurant");
        entity.setEmail("therestaurant@email.com");

        Set<Order> orders = new HashSet<>();
        orders.add(mockOrder.mockEntity(id + 4));

        entity.setOrders(orders);

        Set<Food> foods = new HashSet<>();
        foods.add(mockFood.mockEntity(id + 5));
        foods.add(mockFood.mockEntity(id + 6));

        entity.setFoods(foods);

        return entity;
    }

    public Restaurant mockEntityWithoutOrders(long id) {

        Restaurant entity = new Restaurant();

        entity.setId(id);

        Set<Address> addresses = new HashSet<>();
        addresses.add(mockAddress.mockEntity(id + 2));
        addresses.add(mockAddress.mockEntity(id + 3));

        entity.setAddresses(addresses);
        entity.setCnpj("123456789011");
        entity.setName("The Restaurant");
        entity.setEmail("therestaurant@email.com");

        Set<Food> foods = new HashSet<>();
        foods.add(mockFood.mockEntity(id + 5));
        foods.add(mockFood.mockEntity(id + 6));

        entity.setFoods(foods);

        return entity;
    }

    private static class MockRestaurantHolder {
        public final static MockRestaurant INSTANCE = new MockRestaurant();
    }
}
