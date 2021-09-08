package com.thi.service;

import com.thi.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ICategoryServiceImpl implements iCategoryService{
    @Override
    public Category findById(int id) {
        Category category = null;
        String sql = "select * from category where categoryId = ?;";
        Connection connection = new Connection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int categoryId = resultSet.getInt(1);
                String categoryName = resultSet.getString(2);
                category = new Category(categoryId,categoryName);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "select * from category";
        Connection connection = new Connection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int categoryId = resultSet.getInt(1);
                String categoryName = resultSet.getString(2);
                categoryList.add(new Category(categoryId,categoryName));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return categoryList;
    }
}
