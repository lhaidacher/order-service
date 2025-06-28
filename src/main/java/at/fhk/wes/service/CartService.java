package at.fhk.wes.service;

import at.fhk.wes.domain.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "cart-service", url = "${fhk.ps2.ecommerce.cart-service}")
public interface CartService {
    @GetMapping("/carts")
    Cart getCart();

    @DeleteMapping("/carts")
    void cleanUpCart();
}