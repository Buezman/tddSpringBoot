package com.buezman.testdemo.product;

import com.buezman.testdemo.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public BaseResponse<Product> addProduct(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        Product newProduct = productRepository.save(product);
        return BaseResponse.<Product>builder()
                .status("success")
                .message("New Product Added")
                .data(newProduct)
                .build();
    }

    @Override
    public BaseResponse<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return BaseResponse.<List<Product>>builder()
                .status("success")
                .message(products.size() + " Products retrieved")
                .data(products)
                .build();
    }


}
