package yuri.filgueira.yufoodapi.mapper.modelMapper.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.entities.Food;
import yuri.filgueira.yufoodapi.entities.OrderItem;
import yuri.filgueira.yufoodapi.data.vo.OrderItemVO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<OrderItem, OrderItemVO> typeMapOrderItemToVo = modelMapper.createTypeMap(OrderItem.class, OrderItemVO.class);
        typeMapOrderItemToVo.addMappings(mapper -> {
            mapper.map(OrderItem::getId, OrderItemVO::setKey);
        });
        typeMapOrderItemToVo.addMappings(mapper -> {});

        TypeMap<OrderItemVO, OrderItem> typeMapVoToOrderItem = modelMapper.createTypeMap(OrderItemVO.class, OrderItem.class);
        typeMapVoToOrderItem.addMappings(mapper -> {
            mapper.map(OrderItemVO::getKey, OrderItem::setId);
        });

        TypeMap<Food, FoodVO> typeMapFoodToVo = modelMapper.createTypeMap(Food.class, FoodVO.class);
        typeMapFoodToVo.addMappings(mapper -> {
            mapper.map(Food::getId, FoodVO::setKey);
        });

        TypeMap<FoodVO, Food> typeMapVoToFood = modelMapper.createTypeMap(FoodVO.class, Food.class);
        typeMapVoToFood.addMappings(mapper -> {
            mapper.map(FoodVO::getKey, Food::setId);
        });

        return modelMapper;

    }

}
