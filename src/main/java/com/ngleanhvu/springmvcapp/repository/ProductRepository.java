package com.ngleanhvu.springmvcapp.repository;

import com.ngleanhvu.springmvcapp.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    List<Product> getProducts(Map<String, String> params);
    Product saveOrUpdate(Product p);
    Product getProductById(int id);
    int countProducts();
    boolean deleteProductById(int id);
}
