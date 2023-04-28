package flapkap.vendingmachine.api.mapper;

import flapkap.vendingmachine.api.v1.resources.ProductRequestResource;
import flapkap.vendingmachine.api.v1.resources.ProductResource;
import flapkap.vendingmachine.data.dto.ProductDto;
import flapkap.vendingmachine.data.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto map(ProductRequestResource productRequestResource);

    ProductResource map(Product product);

    List<ProductResource> map(List<Product> product);

    Product map(ProductDto productDto);
}
