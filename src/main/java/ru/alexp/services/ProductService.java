package ru.alexp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexp.entities.Product;
import ru.alexp.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public void removeById(Long id) {
        productRepository.removeById(id);
    }

    public List<Product> filterByKeyWord(String keyword) {
        return productRepository.filterByKeyword(keyword);
    }

    public void editProduct(Product product) {
        productRepository.editProduct(product);
    }
}
