package flapkap.vendingmachine.services;

import flapkap.vendingmachine.api.mapper.ProductMapper;
import flapkap.vendingmachine.data.dto.ProductDto;
import flapkap.vendingmachine.data.entities.Product;
import flapkap.vendingmachine.data.entities.User;
import flapkap.vendingmachine.data.repositories.ProductRepository;
import flapkap.vendingmachine.data.repositories.UserRepository;
import flapkap.vendingmachine.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    User currentUser;

    @Override
    public Page<Product> listProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = this.productMapper.map(productDto);
        User user = this.userRepository.findById(currentUser.getId()).orElseThrow(() ->
                BusinessException.badRequest("User not found", null));
        product.setUser(user);
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        var product = this.getProductById(id);
        this.checkProductOperations(this.currentUser.getId(), product.getUser().getId());
        product.setCost(productDto.getCost());
        product.setName(productDto.getName());
        product.setAmountAvailable(productDto.getAmountAvailable());
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        var product = this.getProductById(id);
        this.checkProductOperations(this.currentUser.getId(), product.getUser().getId());
        this.productRepository.delete(product);
    }

    public Product getProductById(Long id) {
       return this.productRepository.findById(id)
                .orElseThrow(() -> BusinessException.badRequest("Product not found", HttpStatus.BAD_REQUEST.toString()));
    }

    @Override
    public boolean userCanDoProductOperations(Long userId, Long productUserCreatedId) {
        return userId.equals(productUserCreatedId);
    }
    private void checkProductOperations(Long userId, Long productUserCreatedId) {
        if (!userCanDoProductOperations(userId, productUserCreatedId))
            throw BusinessException.unAuthorizedRequest("PUT and DELETE can be called only by the seller user who created the\n" +
                    "product", null);
    }
}
