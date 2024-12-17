package org.eclipse.jakarta.hello.repository;

import jakarta.ejb.Singleton;
import org.eclipse.jakarta.hello.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepository {
    private List<Product> products = new ArrayList<>();
    private static ProductsRepository productsRepository;

    private ProductsRepository() {


    }

    public static ProductsRepository getInstance() {
        if (productsRepository == null) {
            productsRepository = new ProductsRepository();
        }
        return productsRepository;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Optional<Product> findProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void addProduct(Product product) {
        if (findProductByName(product.getName()).isPresent()) {
            throw new IllegalArgumentException("Product with this name already exists.");
        }
        products.add(product);
    }

    public void updateProduct(Product product) {
        deleteProduct(product.getName());
        addProduct(product);
    }

    public void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equalsIgnoreCase(name));
    }


}