package yuri.filgueira.yufoodapi.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends EntityObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(Long id, String name, String email, Set<Address> addresses, String cpf, Set<Order> orders) {
        super(id, name, email, addresses);
        this.cpf = cpf;
        this.orders = orders;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void addOrder(Order item) {
        orders.add(item);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }
}
