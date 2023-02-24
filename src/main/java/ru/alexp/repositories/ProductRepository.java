package ru.alexp.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.alexp.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    // Эмулируем работу с БД
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equalsIgnoreCase(title)).findFirst().get();
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        products.add(product);
    }

    public void removeById(Long id) {
        products.remove(findById(id));
    }

    public List<Product> filterByKeyword(String keyword) {
        return products.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void editProduct(Product product) {
        Product p = findById(product.getId());
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        save(p);
        removeById(product.getId());
    }
}
