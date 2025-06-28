package at.fhk.wes.controller;

import at.fhk.wes.domain.BillingAddress;
import at.fhk.wes.domain.Cart;
import at.fhk.wes.domain.Order;
import at.fhk.wes.domain.Product;
import at.fhk.wes.service.CartService;
import at.fhk.wes.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final Set<Order> orders = new HashSet<>();
    private final CartService cartService;
    private final ProductService productService;

    public OrderController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping
    public Order placeOrder(@RequestBody BillingAddress billingAddress) {
        logger.info("started placeOrder() method");

        Cart cart = cartService.getCart();
        if (cart.getProducts().isEmpty())
            throw new IllegalStateException("Shopping cart is empty");

        cartService.cleanUpCart();
        productService.sellProducts(cart.getProducts());

        Order order = new Order(billingAddress, cart.getProducts());
        orders.add(order);
        return order;
    }

    @GetMapping
    public List<Order> getOrders() {
        logger.info("started getOrders() method");
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt))
                .toList();
    }
}
