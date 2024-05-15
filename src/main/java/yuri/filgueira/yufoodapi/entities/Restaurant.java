package yuri.filgueira.yufoodapi.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@PrimaryKeyJoinColumn(name = "id")
public class Restaurant extends EntityObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Set<Food> foods = new HashSet<>();
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToMany(mappedBy = "restaurant")
    private Set<Order> orders = new HashSet<>();

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String email, Set<Address> addresses, String cnpj, Set<Food> foods, Set<Order> orders) {
        super(id, name, email, addresses);
        this.cnpj = cnpj;
        this.foods = foods;
        this.orders = orders;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void addOrder(Order item) {
        orders.add(item);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }
}
