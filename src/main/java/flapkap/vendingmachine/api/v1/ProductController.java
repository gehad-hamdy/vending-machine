package flapkap.vendingmachine.api.v1;

import flapkap.vendingmachine.api.mapper.ProductMapper;
import flapkap.vendingmachine.api.v1.resources.ProductRequestResource;
import flapkap.vendingmachine.api.v1.resources.ProductResource;
import flapkap.vendingmachine.exceptions.BusinessException;
import flapkap.vendingmachine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController extends AbstractController implements ProductsApi {
    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @Override
    public ResponseEntity<ProductResource> createProduct(ProductRequestResource productRequestResource) {
        try {
            var productDto = this.productMapper.map(productRequestResource);
            var product = this.productService.createProduct(productDto);

            return ResponseEntity.ok().body(this.productMapper.map(product));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        } catch (final InvalidDataAccessApiUsageException |
                       DataIntegrityViolationException exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<ProductResource> updateProduct(Long id, ProductRequestResource productRequestResource) {
        try {
            var productDto = this.productMapper.map(productRequestResource);
            var product = this.productService.updateProduct(id, productDto);
            return ResponseEntity.ok().body(this.productMapper.map(product));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), null);
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<ProductResource>> listProduct(Integer offset, Integer limit) {
        try {
            var page = this.productService.listProducts(pageOf(offset, limit));
            return ResponseEntity.ok()
                    .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                    .body(this.productMapper.map(page.getContent()));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<ProductResource> getProductById(Long id) {
        try {
            return ResponseEntity.ok().body(this.productMapper.map(this.productService.getProductById(id)));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        try {
            this.productService.deleteProduct(id);
            return ProductsApi.super.deleteProduct(id);
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }
}
