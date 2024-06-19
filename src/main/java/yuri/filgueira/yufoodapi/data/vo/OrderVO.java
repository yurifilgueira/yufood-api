package yuri.filgueira.yufoodapi.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OrderVO extends RepresentationModel<OrderVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long key;
    private BigDecimal total;
    @JsonIgnore
    private RestaurantVO restaurant;
    @JsonIgnore
    private CustomerVO customer;

    @JsonIgnore
    private Set<OrderItemVO> orderItems = new HashSet<>();

    public OrderVO() {
    }

    public OrderVO(Long key, BigDecimal total, RestaurantVO restaurant, CustomerVO customer, Set<OrderItemVO> orderItems) {
        this.key = key;
        this.total = total;
        this.restaurant = restaurant;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<OrderItemVO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemVO> orderItems) {
        this.orderItems = orderItems;
    }

    public RestaurantVO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantVO restaurant) {
        this.restaurant = restaurant;
    }

    public CustomerVO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVO customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderVO orderVO)) return false;
        return Objects.equals(getKey(), orderVO.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey());
    }
}