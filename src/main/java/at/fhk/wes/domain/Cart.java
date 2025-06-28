package at.fhk.wes.domain;

import java.util.Set;

public class Cart {
    public Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
