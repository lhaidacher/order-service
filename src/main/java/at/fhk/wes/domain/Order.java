package at.fhk.wes.domain;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class Order {
    public final UUID id;
    public final BillingAddress billingAddress;
    public final Set<Product> products;
    public final Timestamp createdAt;

    public Order(BillingAddress billingAddress, Set<Product> products) {
        this.id = UUID.randomUUID();
        this.billingAddress = billingAddress;
        this.products = products;
        this.createdAt = Timestamp.from(Instant.now());
    }

    public UUID getId() {
        return id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Double getPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
