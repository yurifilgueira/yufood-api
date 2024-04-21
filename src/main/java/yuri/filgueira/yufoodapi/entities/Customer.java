package yuri.filgueira.yufoodapi.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullName;

    private Set<Address> addresses = new HashSet<>();
    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(Long id, String fullName, Set<Address> addresses, Set<Order> orders) {
        this.id = id;
        this.fullName = fullName;
        this.addresses = addresses;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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
