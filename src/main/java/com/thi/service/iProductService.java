package com.thi.service;

import com.thi.model.Product;

import java.util.List;

public interface iProductService {
    void add(Product product);

    void update(int productId, Product product);

    List<Product> findAll();

    void delete(int productId);

    List<Product> findByName(String name);

    Product findById(int productId);
}
