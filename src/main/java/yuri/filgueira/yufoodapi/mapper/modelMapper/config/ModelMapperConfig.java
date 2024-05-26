package yuri.filgueira.yufoodapi.mapper.modelMapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.data.vo.OrderItemVO;
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

        return modelMapper;
    }
}
