package yuri.filgueira.yufoodapi.data.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RestaurantVO extends EntityObjectVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String cnpj;

    private Set<FoodVO> foods = new HashSet<>();
    private Set<OrderVO> orders = new HashSet<>();

    public RestaurantVO() {
    }

    public RestaurantVO(Long id, String name, String email, Set<AddressVO> addresses, String cnpj, Set<FoodVO> foods, Set<OrderVO> orders) {
        super(id, name, email, addresses);
        this.cnpj = cnpj;
        this.foods = foods;
        this.orders = orders;
    }

    public Set<FoodVO> getFoodVOs() {
        return foods;
    }

    public void setFoodVOs(Set<FoodVO> foods) {
        this.foods = foods;
    }

    public Set<OrderVO> getOrderVOs() {
        return orders;
    }

    public void setOrderVOs(Set<OrderVO> orders) {
        this.orders = orders;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void addOrderVO(OrderVO item) {
        orders.add(item);
    }

    public void removeOrderVO(OrderVO order) {
        orders.remove(order);
    }
}
