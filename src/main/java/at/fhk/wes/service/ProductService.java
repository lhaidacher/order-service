package at.fhk.wes.service;

import at.fhk.wes.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient(name = "product-service", url = "${fhk.ps2.ecommerce.product-service}")
public interface ProductService {
    @PutMapping("/products/sell")
    void sellProducts(@RequestBody Set<Product> products);
}