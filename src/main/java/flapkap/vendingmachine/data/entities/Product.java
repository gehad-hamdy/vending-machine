package flapkap.vendingmachine.data.entities;

import lombok.*;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends TimestampedEntity {
    private String name;

    private Double cost;

    private Integer amountAvailable;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup("users")
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
