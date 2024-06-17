package yuri.filgueira.yufoodapi.data.vo;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class EntityObjectVO extends RepresentationModel<EntityObjectVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Long key;
    protected String name;
    protected String email;

    private Set<AddressVO> addresses =  new HashSet<>();

    public EntityObjectVO() {
    }

    public EntityObjectVO(Long key, String name, String email, Set<AddressVO> addresses) {
        this.key = key;
        this.name = name;
        this.email = email;
        this.addresses = addresses;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AddressVO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressVO> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(AddressVO address) {
        addresses.add(address);
    }

    public void removeAddress(AddressVO address) {
        addresses.remove(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityObjectVO that)) return false;
        return Objects.equals(getKey(), that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey());
    }
}
