package yuri.filgueira.yufoodapi.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends EntityObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
