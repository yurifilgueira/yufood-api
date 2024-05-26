package yuri.filgueira.yufoodapi.unitTests.mapper.mocks;


import yuri.filgueira.yufoodapi.data.vo.FoodVO;
import yuri.filgueira.yufoodapi.entities.Food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockFood {

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

        for (int i = 0; i < 9; i++) {
            Food entity = mockEntity(i);
            entity.setPrice(new BigDecimal(20 * i));
            entity.setName("Name" + i);

            entities.add(entity);
        }

        return entities;

    }

    public List<FoodVO> mockVOList() {
        List<FoodVO> vos = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            FoodVO vo = mockVO(i);
            vo.setPrice(new BigDecimal(20 * i));
            vo.setName("Name" + i);

            vos.add(vo);
        }

        return vos;
    }
}