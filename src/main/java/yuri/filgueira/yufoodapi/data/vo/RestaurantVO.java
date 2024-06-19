package yuri.filgueira.yufoodapi.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RestaurantVO extends EntityObjectVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String cnpj;

    @JsonIgnore
    private Set<FoodVO> foods = new HashSet<>();
    @JsonIgnore
    private Set<OrderVO> orders = new HashSet<>();

    public RestaurantVO() {
    }

    public RestaurantVO(Long key, String name, String email, Set<AddressVO> addresses, String cnpj, Set<FoodVO> foods, Set<OrderVO> orders) {
        super(key, name, email, addresses);
        this.cnpj = cnpj;
        this.foods = foods;
        this.orders = orders;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Set<FoodVO> getFoods() {
        return foods;
    }

    public void setFoods(Set<FoodVO> foods) {
        this.foods = foods;
    }

    public Set<OrderVO> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderVO> orders) {
        this.orders = orders;
    }
}
