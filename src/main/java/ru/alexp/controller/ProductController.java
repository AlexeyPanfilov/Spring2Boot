package ru.alexp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexp.entities.Product;
import ru.alexp.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductsList(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        Product product = new Product();
        if (keyword == null) {
            model.addAttribute("products", productService.getAllProducts());
        }
        model.addAttribute("product", product);
        if (keyword != null) {
            model.addAttribute("keyword", keyword);
            model.addAttribute("products", productService.filterByKeyWord(keyword));
        }
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productService.add(product);
        System.out.println(product.getTitle());
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productService.removeById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        System.out.println("edit:");
        System.out.println(product);
        return "edit-product";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute(value = "product") Product product) {
        productService.editProduct(product);
        return "redirect:/products";
    }
}
