package yuri.filgueira.yufoodapi.entities;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal total;
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public Order() {
    }

    public Order(Long id, Set<OrderItem> orderItems, BigDecimal total) {
        this.id = id;
        this.orderItems = orderItems;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
