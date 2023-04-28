package flapkap.vendingmachine.data.dto;

import java.util.List;

public record ProductPurchasedResponse(Double totalPrice, List<ProductPurchasedDto> products) {
}
