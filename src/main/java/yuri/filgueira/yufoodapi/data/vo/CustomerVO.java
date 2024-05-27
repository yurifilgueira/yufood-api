package yuri.filgueira.yufoodapi.data.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CustomerVO extends EntityObjectVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String cpf;

    private Set<OrderVO> orders = new HashSet<>();

    public CustomerVO() {
    }

    public CustomerVO(String cpf, Set<OrderVO> orders) {
        this.cpf = cpf;
        this.orders = orders;
    }

    public CustomerVO(Long key, String name, String email, Set<AddressVO> addresses, String cpf, Set<OrderVO> orders) {
        super(key, name, email, addresses);
        this.cpf = cpf;
        this.orders = orders;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<OrderVO> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderVO> orders) {
        this.orders = orders;
    }

}
