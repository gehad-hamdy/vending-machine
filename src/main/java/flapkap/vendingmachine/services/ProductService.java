package flapkap.vendingmachine.services;

import flapkap.vendingmachine.data.entities.Product;

import java.util.List;

public interface ProductService {
    public List<Product> listProducts();
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Product product);
    public void getProductById(Long id);
}
