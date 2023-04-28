package flapkap.vendingmachine.api.mapper;

import flapkap.vendingmachine.api.v1.resources.ProductBuyRequestResource;
import flapkap.vendingmachine.api.v1.resources.ProductBuyResponseResource;
import flapkap.vendingmachine.data.dto.ProductPurchasedDto;
import flapkap.vendingmachine.data.dto.ProductPurchasedResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductPurchasedMapper {
    ProductPurchasedDto map(ProductBuyRequestResource productBuyRequestResource);

    List<ProductPurchasedDto> map(List<ProductBuyRequestResource> productBuyRequestResource);

    ProductBuyResponseResource map(ProductPurchasedResponse purchasedResponse);
}
