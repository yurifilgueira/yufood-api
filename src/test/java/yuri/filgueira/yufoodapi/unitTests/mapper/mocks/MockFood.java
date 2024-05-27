package yuri.filgueira.yufoodapi.unitTests.mapper.mocks;


import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.entities.Food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockFood {

    public static MockFood getMockFood(){
        return MockFoodHolder.INSTANCE;
    }

    public Food mockEntity(long id) {
        Food entity = new Food();
        entity.setId(id);
        entity.setName("Prato do dia");
        entity.setPrice(new BigDecimal("23.90"));

        return entity;
    }

    public FoodVO mockVO(long key) {
        FoodVO vo = new FoodVO();
        vo.setKey(key);
        vo.setName("Prato do dia");
        vo.setPrice(new BigDecimal("23.90"));

        return vo;
    }

    public List<Food> mockEntityList() {
        List<Food> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Food entity = mockEntity(i);
            entity.setName("Name: " + i);
            entity.setPrice(new BigDecimal(20 + i));

            entities.add(entity);
        }

        return entities;

    }

    public List<FoodVO> mockVOList() {
        List<FoodVO> vos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            FoodVO vo = mockVO(i);
            vo.setName("Name: " + i);
            vo.setPrice(new BigDecimal(20 + i));

            vos.add(vo);
        }

        return vos;
    }

    private static class MockFoodHolder {
        public final static MockFood INSTANCE = new MockFood();
    }
}
