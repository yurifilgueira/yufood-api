package yuri.filgueira.yufoodapi.mapper.modelMapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yuri.filgueira.yufoodapi.data.vo.AddressVO;
import yuri.filgueira.yufoodapi.data.vo.EntityObjectVO;
import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.data.vo.OrderItemVO;
import yuri.filgueira.yufoodapi.entities.Address;
import yuri.filgueira.yufoodapi.entities.EntityObject;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.entities.OrderItem;

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

        return modelMapper;
    }
}
