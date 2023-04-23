package flapkap.vendingmachine.services;

import flapkap.vendingmachine.data.entities.Product;
import flapkap.vendingmachine.data.repositories.ProductRepository;
import flapkap.vendingmachine.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> listProducts() {
        return this.productRepository.findAll();
    }

    @Transactional
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Product product) {
        this.productRepository.delete(product);
    }

    public void getProductById(Long id) {
        this.productRepository.findById(id)
                .orElseThrow(() -> BusinessException.badRequest("Product not found", HttpStatus.BAD_REQUEST.toString()));
    }
}
