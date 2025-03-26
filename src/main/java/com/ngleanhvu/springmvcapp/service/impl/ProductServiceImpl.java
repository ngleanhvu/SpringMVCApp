package com.ngleanhvu.springmvcapp.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ngleanhvu.springmvcapp.pojo.Product;
import com.ngleanhvu.springmvcapp.repository.ProductRepository;
import com.ngleanhvu.springmvcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        return this.prodRepo.getProducts(params);
    }

    @Override
    public Product saveOrUpdate(Product p) {
        if (!p.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(), ObjectUtils.asMap("resource_type","auto"));
                p.setImage((String) res.get("secure_url"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.prodRepo.saveOrUpdate(p);
    }

    @Override
    public Product getProductById(int id) {
        return this.prodRepo.getProductById(id);
    }

    @Override
    public int countProducts() {
        return this.prodRepo.countProducts();
    }

    @Override
    public void deleteProductById(int id) {
        this.prodRepo.deleteProductById(id);
    }
}
