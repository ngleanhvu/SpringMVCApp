package com.ngleanhvu.springmvcapp.service.impl;

import com.ngleanhvu.springmvcapp.pojo.Category;
import com.ngleanhvu.springmvcapp.repository.CategoryRepository;
import com.ngleanhvu.springmvcapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository cateRepo;

    @Override
    public List<Category> getCates() {
        return this.cateRepo.getCates();
    }
}
