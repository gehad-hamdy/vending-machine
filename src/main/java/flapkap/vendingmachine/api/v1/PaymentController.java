package flapkap.vendingmachine.api.v1;

import flapkap.vendingmachine.api.mapper.ProductPurchasedMapper;
import flapkap.vendingmachine.api.mapper.UserMapper;
import flapkap.vendingmachine.api.v1.resources.ProductBuyRequestResource;
import flapkap.vendingmachine.api.v1.resources.ProductBuyResponseResource;
import flapkap.vendingmachine.api.v1.resources.UserDepositResource;
import flapkap.vendingmachine.api.v1.resources.UserResponseResource;
import flapkap.vendingmachine.exceptions.BusinessException;
import flapkap.vendingmachine.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("hasRole('buyer')")
public class PaymentController extends AbstractController implements PaymentApi {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductPurchasedMapper productPurchasedMapper;

    @Override
    public ResponseEntity<UserResponseResource> deposit(UserDepositResource userDepositResource) {
        try {
            Integer value = userDepositResource.getDepositValue().getValue();
            var user = this.paymentService.setDepositValue(value);

            return ResponseEntity.ok().body(this.userMapper.map(user));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), null);
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<UserResponseResource> reset() {
        try {
            var user = this.paymentService.resetLastDeposit();

            return ResponseEntity.ok().body(this.userMapper.map(user));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), null);
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<ProductBuyResponseResource> buyProduct(List<ProductBuyRequestResource> productBuyRequestResource) {
       var products = this.productPurchasedMapper.map(productBuyRequestResource);
        try {
            var purchasedResponse = this.paymentService.purchasedProducts(products);

            return ResponseEntity.ok().body(this.productPurchasedMapper.map(purchasedResponse));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), null);
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }
}
