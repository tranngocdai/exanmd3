package com.thi.service;

import com.thi.model.Category;

import java.util.List;

public interface iCategoryService {
    Category findById(int id);

    List<Category> findAll();

}
