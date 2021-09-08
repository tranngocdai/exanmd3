package com.thi.service;

import com.thi.model.Category;
import com.thi.model.Product;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IProductServiceImpl implements iProductService {
    private static final String ADD_PRODUCT =
            "insert into product(productName, productPrice, productQuantity,productColor,productDesc,categoryId) values (?,?,?,?,?,?);";
    private static final String FIND_ALL_PRODUCT = "select * from product;";
    private static final String DELETE_PRODUCT = "delete from product where productId = ?;";

    @Override
    public void add(Product product) {
        Connection connection = new Connection();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getProductQuantity());
            preparedStatement.setString(4, product.getProductColor());
            preparedStatement.setString(5, product.getProductDesc());
            Category category = product.getCategory();
            int categoryId = category.getCategoryId();
            preparedStatement.setInt(6,categoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(int productId, Product product) {
        Connection connection = new Connection();
        try {
            CallableStatement callableStatement = connection.getConnection().prepareCall("{call updateProduct(?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, productId);
            callableStatement.setString(2, product.getProductName());
            callableStatement.setInt(3, product.getProductPrice());
            callableStatement.setInt(4, product.getProductQuantity());
            callableStatement.setString(5, product.getProductColor());
            callableStatement.setString(6, product.getProductDesc());
            int categoryId = product.getCategory().getCategoryId();
            callableStatement.setInt(7, categoryId);
            callableStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        Connection connection = new Connection();
        iCategoryService iCategoryService = new ICategoryServiceImpl();
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(FIND_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String productName = resultSet.getString(2);
                int productPrice = resultSet.getInt(3);
                int productQuantity = resultSet.getInt(4);
                String productColor = resultSet.getString(5);
                String productDesc = resultSet.getString(6);
                int categoryId = resultSet.getInt(7);
                Category category = iCategoryService.findById(categoryId);
                productList.add(new Product(productId, productName, productPrice, productQuantity, productColor, productDesc, category));
//                productName, productPrice, productQuantity,productColor,productDesc,categoryId
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void delete(int productId) {
        Connection connection = new Connection();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Product> findByName(String name) {
        Connection connection = new Connection();
        iCategoryService iCategoryService = new ICategoryServiceImpl();
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareCall("{ select * from product where name like?");
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String productName = resultSet.getString(2);
                int productPrice = resultSet.getInt(3);
                int productQuantity = resultSet.getInt(4);
                String productColor = resultSet.getString(5);
                String productDesc = resultSet.getString(6);
                int categoryId = resultSet.getInt(7);
                Category category = iCategoryService.findById(categoryId);
                productList.add(new Product(productId, productName, productPrice, productQuantity, productColor, productDesc, category));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product findById(int productId) {
        Connection connection = new Connection();
        iCategoryService iCategoryService = new ICategoryServiceImpl();
        String sql = "select * from product where productId =?;";
        PreparedStatement preparedStatement = null;
        Product product = null;
        try {
            preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String productName = resultSet.getString(2);
                int productPrice = resultSet.getInt(3);
                int productQuantity = resultSet.getInt(4);
                String productColor = resultSet.getString(5);
                String productDesc = resultSet.getString(6);
                int categoryId = resultSet.getInt(7);
                Category category = iCategoryService.findById(categoryId);
                product = new Product(id,productName,productPrice,productQuantity,productColor,productDesc,category);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }
}
