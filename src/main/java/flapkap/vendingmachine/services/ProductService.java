package flapkap.vendingmachine.services;

import flapkap.vendingmachine.data.dto.ProductDto;
import flapkap.vendingmachine.data.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public Page<Product> listProducts(Pageable pageable);
    public Product createProduct(ProductDto productDto);
    public Product updateProduct(Long id, ProductDto productDto);
    public void deleteProduct(Long id);
    public Product getProductById(Long id);

    public boolean userCanDoProductOperations(Long userId, Long productUserCreatedId);
}
