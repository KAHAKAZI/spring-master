package pl.training.shop;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.orders.Order;
import pl.training.shop.orders.OrderService;
import pl.training.shop.payments.*;
import pl.training.shop.products.Product;
import pl.training.shop.products.ProductType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log
public class Application {

    // location of spring configuration
    private static final String BASE_PACKAGE = "pl.training.shop";
    private static final Product VIDEO_PRODUCT = Product.builder()
            .name("Spring masterclass")
            .description("Praktyczny kurs Spring Framework")
            .type(ProductType.VIDEO)
            .price(LocalMoney.of(799))
            .build();

    private static final Product BOOK_PRODUCT = Product.builder()
            .name("Spring masterclass")
            .description("Praktyczne ćwiczenie do samodzielnego wykonania")
            .type(ProductType.BOOK)
            .price(LocalMoney.of(799))
            .build();

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE)) {
            var ShopService = applicationContext.getBean(pl.training.shop.ShopService.class);
            ShopService.addProduct(VIDEO_PRODUCT);
            ShopService.addProduct(BOOK_PRODUCT);
            log.info(ShopService.getProducts(0, 100).toString());

            var order = new Order(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));
            ShopService.placeOrder(order);
            var payment = ShopService.payForOrder(order.getId());
            log.info(payment.getId());

        }
    }
}
