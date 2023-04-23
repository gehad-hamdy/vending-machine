package flapkap.vendingmachine.data.entities;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends TimestampedEntity{
    @NotNull
    private String userName;

    private Double deposit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup("roles")
    private Role role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
