package com.ngleanhvu.springmvcapp.service.impl;

import com.ngleanhvu.springmvcapp.pojo.Product;
import com.ngleanhvu.springmvcapp.repository.ProductRepository;
import com.ngleanhvu.springmvcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository prodRepo;

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        return this.prodRepo.getProducts(params);
    }

    @Override
    public Product saveOrUpdate(Product p) {
        p.setImage("https://res.cloudinary.com/dxxwcby8l/image/upload/v1710141425/bufd2mlepddhu4mh53ev.png");
        return this.prodRepo.saveOrUpdate(p);
    }

    @Override
    public Product getProductById(int id) {
        return this.prodRepo.getProductById(id);
    }
}
