package com.buba.service;

import com.buba.dao.CategoryDao;
import com.buba.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> list(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return categoryDao.findAll(sort);
    }

    public Category get(int id) {
        Category c= categoryDao.findById(id).orElse(null);
        return c;
    }
}
