package yuri.filgueira.yufoodapi.data.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AddressVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long key;
    private String zipCode;
    private String city;
    private String state;
    private String number;
    private String street;
    private String complement;
    private String neighborhood;

    public AddressVO() {
    }

    public AddressVO(Long key, String zipCode, String city, String state, String number, String street, String complement, String neighborhood) {
        this.key = key;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.number = number;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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
        if (!(o instanceof AddressVO addressVO)) return false;
        return Objects.equals(getKey(), addressVO.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey());
    }
}
