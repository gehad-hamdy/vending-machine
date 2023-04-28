package flapkap.vendingmachine.services;

import flapkap.vendingmachine.data.dto.ProductPurchasedDto;
import flapkap.vendingmachine.data.dto.ProductPurchasedResponse;
import flapkap.vendingmachine.data.entities.User;

import java.util.List;

public interface PaymentService {
     User setDepositValue(Integer value);
     User resetLastDeposit();

     ProductPurchasedResponse purchasedProducts(List<ProductPurchasedDto> productPurchasedDto);
}
