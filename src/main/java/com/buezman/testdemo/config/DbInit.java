package com.buezman.testdemo.config;

import com.buezman.testdemo.product.Product;
import com.buezman.testdemo.product.ProductRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit {

    private final ProductRepository productRepository;

    @PostConstruct
    void seedProducts() {
        log.info("..........Seeding Products Table...........");
        long count = productRepository.count();
        log.info("..........Total Size :: {}............", count);
        if (count > 0) return;
        Faker faker = Faker.instance();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Product product = Product.builder()
                    .name(faker.commerce().productName())
                    .price(new BigDecimal(faker.commerce().price()))
                    .units(Integer.parseInt(RandomStringUtils.randomNumeric(1,2)))
                    .build();
            products.add(product);
        }
        productRepository.saveAll(products);
    }
}
