package com.example.spring_7_11.Controllers;

import com.example.spring_7_11.Model.Product;
import com.example.spring_7_11.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    // GET /products -> show list + form
    @GetMapping("/products")
    public String viewProducts(Model model)
    {
        var products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    // POST /products -> add product
    @PostMapping("/products")
    public String addProduct(
            @RequestParam String name,
            @RequestParam(required = false, defaultValue = "0") Double price,
            Model model
    ) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        productService.addProduct(p);

        var products = productService.findAll();
        model.addAttribute("products", products);

        return "products";
    }

}
