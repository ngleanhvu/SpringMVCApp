package com.ngleanhvu.springmvcapp.service;

import com.ngleanhvu.springmvcapp.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getProducts(Map<String, String> params);
    Product saveOrUpdate(Product p);
    Product getProductById(int id);
    int countProducts();
    void deleteProductById(int id);
}
