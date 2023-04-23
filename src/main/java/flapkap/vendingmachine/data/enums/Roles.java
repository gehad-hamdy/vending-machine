package flapkap.vendingmachine.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Roles {
    SELLER(1),
    BUYER(2);

    private final Integer id;

    private Roles(final Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return this.id;
    }
}
