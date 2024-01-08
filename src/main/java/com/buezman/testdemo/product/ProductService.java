package com.buezman.testdemo.product;

import com.buezman.testdemo.config.BaseResponse;

import java.util.List;

public interface ProductService {
    BaseResponse<Product> addProduct(ProductRequest productRequest);
    BaseResponse<List<Product>> getAllProducts();
}
