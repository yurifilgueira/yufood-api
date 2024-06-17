package yuri.filgueira.yufoodapi.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
@JsonPropertyOrder(value = {"id", "name", "price"})
public class FoodVO extends RepresentationModel<FoodVO>  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long key;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal price;

    public FoodVO() {
    }

    public FoodVO(Long key, String name, BigDecimal price) {
        this.key = key;
        this.name = name;
        this.price = price;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodVO foodVO)) return false;
        return Objects.equals(getKey(), foodVO.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey());
    }
}
