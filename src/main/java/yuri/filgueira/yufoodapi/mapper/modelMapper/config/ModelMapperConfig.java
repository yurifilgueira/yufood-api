package yuri.filgueira.yufoodapi.mapper.modelMapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yuri.filgueira.yufoodapi.data.vo.*;
import yuri.filgueira.yufoodapi.entities.*;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Food.class, FoodVO.class)
                .addMapping(Food::getId, FoodVO::setKey);

        modelMapper.createTypeMap(FoodVO.class, Food.class)
                .addMapping(FoodVO::getKey, Food::setId);

        modelMapper.createTypeMap(OrderItem.class, OrderItemVO.class)
                .addMapping(OrderItem::getId, OrderItemVO::setKey);

        modelMapper.createTypeMap(OrderItemVO.class, OrderItem.class)
                .addMapping(OrderItemVO::getKey, OrderItem::setId);

        modelMapper.createTypeMap(Address.class, AddressVO.class)
                .addMapping(Address::getId, AddressVO::setKey);

        modelMapper.createTypeMap(AddressVO.class, Address.class)
                .addMapping(AddressVO::getKey, Address::setId);

        modelMapper.createTypeMap(EntityObject.class, EntityObjectVO.class)
                .addMapping(EntityObject::getId, EntityObjectVO::setKey);

        modelMapper.createTypeMap(EntityObjectVO.class, EntityObject.class)
                .addMapping(EntityObjectVO::getKey, EntityObject::setId);

        modelMapper.createTypeMap(Restaurant.class, RestaurantVO.class)
                .addMapping(Restaurant::getId, RestaurantVO::setKey);

        modelMapper.createTypeMap(RestaurantVO.class, Restaurant.class)
                .addMapping(RestaurantVO::getKey, Restaurant::setId);

        modelMapper.createTypeMap(Customer.class, CustomerVO.class)
                .addMapping(Customer::getId, CustomerVO::setKey);

        modelMapper.createTypeMap(CustomerVO.class, Customer.class)
                .addMapping(CustomerVO::getKey, Customer::setId);

        modelMapper.createTypeMap(Order.class, OrderVO.class)
                .addMapping(Order::getId, OrderVO::setKey);

        modelMapper.createTypeMap(OrderVO.class, Order.class)
                .addMapping(OrderVO::getKey, Order::setId);
        return modelMapper;
    }
}
