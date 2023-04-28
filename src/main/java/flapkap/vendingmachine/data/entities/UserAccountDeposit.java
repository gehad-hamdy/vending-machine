package flapkap.vendingmachine.data.entities;

import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "user_account_deposit")
public class UserAccountDeposit extends TimestampedEntity{
    private Integer depositValue;
    private Boolean reset;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup("users")
    private User user;

    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public Integer getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(Integer depositValue) {
        this.depositValue = depositValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
