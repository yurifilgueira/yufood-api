package yuri.filgueira.yufoodapi.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "zip_code", nullable = false, length = 8)
    private String zipCode;
    @Column(nullable = false, length = 30)
    private String city;
    @Column(nullable = false, length = 2)
    private String state;
    @Column(nullable = false, length = 5)
    private String number;
    @Column(nullable = false, length = 50)
    private String street;
    @Column(nullable = false, length = 60)
    private String complement;
    @Column(nullable = false, length = 30)
    private String neighborhood;

    public Address() {}

    public Address(String zipCode, String city, String state, String number, String street, String complement, String neighborhood) {
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.number = number;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
