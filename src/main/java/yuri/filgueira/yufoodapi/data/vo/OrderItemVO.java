package yuri.filgueira.yufoodapi.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@JsonPropertyOrder(value = {"id", "quantity", "subtotal", "food"})
public class OrderItemVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long key;
    private Integer quantity;
    private BigDecimal subtotal;

    private FoodVO food;

    public OrderItemVO() {
    }

    public OrderItemVO(Long key, Integer quantity, BigDecimal subtotal, FoodVO food) {
        this.key = key;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.food = food;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public FoodVO getFood() {
        return food;
    }

    public void setFood(FoodVO food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemVO vo)) return false;
        return Objects.equals(getKey(), vo.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey());
    }
}
