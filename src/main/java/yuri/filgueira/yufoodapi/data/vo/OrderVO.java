package yuri.filgueira.yufoodapi.data.vo;

import yuri.filgueira.yufoodapi.entities.Customer;
import yuri.filgueira.yufoodapi.entities.Restaurant;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OrderVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long key;
    private BigDecimal total;
    private Restaurant restaurant;
    private Customer customer;

    private Set<OrderItemVO> orderItems = new HashSet<>();

    public OrderVO() {
    }

    public OrderVO(Long key, BigDecimal total, Restaurant restaurant, Customer customer, Set<OrderItemVO> orderItems) {
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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
