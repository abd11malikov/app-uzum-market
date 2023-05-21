package service.category;

import model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImp implements CategoryService{
    @Override
    public int create(Category category) throws SQLException {
        var connection = getConnection();
        String query = "select add_product(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,category.getName());
        ResultSet resultSet = preparedStatement.executeQuery();
        int result = -1;
        if (resultSet.next()){
            result = resultSet.getInt(1);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        var connection = getConnection();
        String query = "delete from category where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public Category get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select from category where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Category category = null;
        if(resultSet.next()){
            category = Category.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return category;
    }

    @Override
    public List<Category> getALl() throws SQLException {
        var connection = getConnection();
        String query = "select * from category";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Category> categories = new ArrayList<>();
        while (resultSet.next()){
            Category category = Category.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();
            categories.add(category);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return categories;
    }

    @Override
    public Category getCategoryByName(String name) throws SQLException {
        var connection = getConnection();
        String query = "select * from get_category_by_name(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        Category category = null;
        while (resultSet.next()){
            category = Category.builder()
                    .id(resultSet.getInt("i_id"))
                    .name(resultSet.getString("i_name"))
                    .build();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return category;
    }
}
