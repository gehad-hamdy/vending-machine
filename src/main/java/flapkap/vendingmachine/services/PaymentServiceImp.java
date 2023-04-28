package flapkap.vendingmachine.services;

import flapkap.vendingmachine.data.dto.ProductPurchasedDto;
import flapkap.vendingmachine.data.dto.ProductPurchasedResponse;
import flapkap.vendingmachine.data.entities.BuyerProductsPurchased;
import flapkap.vendingmachine.data.entities.Product;
import flapkap.vendingmachine.data.entities.User;
import flapkap.vendingmachine.data.entities.UserAccountDeposit;
import flapkap.vendingmachine.data.repositories.BuyerProductsPurchasedRepository;
import flapkap.vendingmachine.data.repositories.ProductRepository;
import flapkap.vendingmachine.data.repositories.UserAccountDepositRepository;
import flapkap.vendingmachine.data.repositories.UserRepository;
import flapkap.vendingmachine.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PaymentServiceImp implements PaymentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccountDepositRepository userAccountDepositRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyerProductsPurchasedRepository buyerProductsPurchasedRepository;

    @Autowired
    private User currentUser;

    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                BusinessException.badRequest("User not found", HttpStatus.BAD_REQUEST.toString()));
    }

    @Override
    public User setDepositValue(Integer value) {
        User user = this.getUserById(this.currentUser.getId());
        UserAccountDeposit userAccount = new UserAccountDeposit();
        userAccount.setUser(user);
        userAccount.setReset(false);
        userAccount.setDepositValue(value);
        this.userAccountDepositRepository.save(userAccount);

        user.setDeposit(user.getDeposit() + value.doubleValue());
        return this.userRepository.save(user);
    }

    @Override
    public User resetLastDeposit() {
        UserAccountDeposit lastDeposit = this.userAccountDepositRepository.findFirstByUserIdOrderByCreatedAtDesc(this.currentUser.getId())
                .orElseThrow(() -> BusinessException.badRequest("No Deposit to rollback", null));
        lastDeposit.setReset(true);
        this.userAccountDepositRepository.save(lastDeposit);

        User user = this.getUserById(this.currentUser.getId());
        user.setDeposit(user.getDeposit() - lastDeposit.getDepositValue());
        return this.userRepository.save(user);
    }

    @Override
    public ProductPurchasedResponse purchasedProducts(List<ProductPurchasedDto> productPurchasedDto) {
        List<Long> productIds = productPurchasedDto.stream().map(ProductPurchasedDto::getProductId).toList();
        List<Product> products = this.productRepository.findByIdIn(productIds);

        if (productPurchasedDto.size() != products.size()) {
            throw BusinessException.badRequest("Please inter exist products", null);
        }

        double totalPrice = 0.0;
        User user = this.getUserById(this.currentUser.getId());
        //get User deposit value
        double maxPrice = user.getDeposit();
        if (maxPrice <= 0)
            throw BusinessException.badRequest("The user doesn't have enough money to purchase products", null);

        return buildProductPurchasedData(user, productPurchasedDto, products, totalPrice, maxPrice);

    }

    private ProductPurchasedResponse buildProductPurchasedData(User user, List<ProductPurchasedDto> productPurchasedDto, List<Product> products,

                                                               double totalPrice, double maxPrice) {
        List<BuyerProductsPurchased> buyerProductsPurchasedList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<ProductPurchasedDto> productPurchasedResponse = new ArrayList<>();

        for (ProductPurchasedDto product : productPurchasedDto) {
            Product currentProduct = products.stream().filter(p -> Objects.equals(p.getId(), product.getProductId())).findFirst().get();
            double productPrice = currentProduct.getCost() * product.getProductAmount();
            if (currentProduct.getAmountAvailable() >= product.getProductAmount()) {
                if (productPrice <= maxPrice) {
                    //set purchased data
                    BuyerProductsPurchased buyerProductsPurchased = setPurchasedProduct(currentProduct, user, product);
                    buyerProductsPurchasedList.add(buyerProductsPurchased);

                    //update current product availability amount
                    currentProduct.setAmountAvailable(currentProduct.getAmountAvailable() - product.getProductAmount());
                    productList.add(currentProduct);

                    //build the new response product
                    productPurchasedResponse.add(product);

                    maxPrice -= productPrice;
                    totalPrice += productPrice;
                } else {
                    Integer maxCount = customizeProductAmount(maxPrice, product.getProductAmount(), currentProduct.getCost());
                    if (maxCount != 0) {
                        productPrice = currentProduct.getCost() * maxCount;

                        //set purchased data
                        BuyerProductsPurchased buyerProductsPurchased = setPurchasedProduct(currentProduct, user, product);
                        buyerProductsPurchasedList.add(buyerProductsPurchased);

                        //update current product availability amount
                        currentProduct.setAmountAvailable(currentProduct.getAmountAvailable() - product.getProductAmount());
                        productList.add(currentProduct);

                        //update max quantity and build the new response product
                        product.setProductAmount(maxCount);
                        productPurchasedResponse.add(product);

                        maxPrice -= productPrice;
                        totalPrice += productPrice;
                    }
                }
            }
        }

        if (productList.size() > 0) {
            bulkSetBuyerProductPurchased(buyerProductsPurchasedList);
            maintainUserDeposit(user, totalPrice);
            updateProductAvailableStock(productList);
        }

        return new ProductPurchasedResponse(totalPrice, productPurchasedResponse);
    }

    private void bulkSetBuyerProductPurchased(List<BuyerProductsPurchased> buyerProductsPurchasedList) {
        this.buyerProductsPurchasedRepository.saveAll(buyerProductsPurchasedList);
    }

    private void maintainUserDeposit(User user, double totalPrice) {
        user.setDeposit(user.getDeposit() - totalPrice);
        this.userRepository.save(user);
    }

    private void updateProductAvailableStock(List<Product> productList) {
        this.productRepository.saveAll(productList);
    }

    private Integer customizeProductAmount(double maxPrice, Integer amount, double price) {
        while (amount != 0) {
            if (amount * price > maxPrice)
                amount--;
            else break;
        }
        return amount;
    }

    private BuyerProductsPurchased setPurchasedProduct(Product currentProduct, User user, ProductPurchasedDto product) {
        BuyerProductsPurchased buyerProductsPurchased = new BuyerProductsPurchased();
        buyerProductsPurchased.setProduct(currentProduct);
        buyerProductsPurchased.setUser(user);
        buyerProductsPurchased.setPrice(currentProduct.getCost());
        buyerProductsPurchased.setQuantity(product.getProductAmount());

        return buyerProductsPurchased;
    }
}
