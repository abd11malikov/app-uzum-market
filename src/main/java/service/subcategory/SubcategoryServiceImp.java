package service.subcategory;

import model.SubCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryServiceImp implements SubcategoryService{
    @Override
    public int create(SubCategory subCategory) throws SQLException {
        var connection = getConnection();
        String query = "select add_subcategory(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,subCategory.getName());
        preparedStatement.setInt(2,subCategory.getCategoryId());
        ResultSet resultSet = preparedStatement.executeQuery();
        var result = -1;
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
        String query = "delete from subcategory where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public SubCategory get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select from subcategory where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        SubCategory subCategory = null;
        if(resultSet.next()){
            subCategory = SubCategory.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .categoryId(resultSet.getInt("category_id"))
                    .build();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return subCategory;
    }

    @Override
    public List<SubCategory> getALl() throws SQLException {
        var connection = getConnection();
        String query = "select * from subcategory";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<SubCategory> subcategories = new ArrayList<>();
        while (resultSet.next()){
            SubCategory subCategory = SubCategory.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .categoryId(resultSet.getInt("category_id"))
                    .build();
            subcategories.add(subCategory);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return subcategories;
    }
}
