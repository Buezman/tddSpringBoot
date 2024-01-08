package com.buezman.testdemo.product;

import com.buezman.testdemo.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public BaseResponse<Product> addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @GetMapping
    public BaseResponse<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }
}
