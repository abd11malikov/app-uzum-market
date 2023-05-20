package service.product;

import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImp implements ProductService{
    @Override
    public int create(Product product) throws SQLException {
        var connection = getConnection();
        String query = "select add_product(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setDouble(2,product.getPrice());
        preparedStatement.setInt(3,product.getSubcategoryId());
        preparedStatement.setString(4,product.getDescription());
        preparedStatement.setString(5, product.getColor());
        preparedStatement.setString(6,product.getSize());
        preparedStatement.setInt(7,product.getOwnerId());
        preparedStatement.setInt(8,product.getAmount());
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
        String query = "delete from product where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public Product get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select * from product where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = null;
        if(resultSet.next()){
            product = Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getDouble("price"))
                    .subcategoryId(resultSet.getInt("subcategory_id"))
                    .description(resultSet.getString("description"))
                    .color(resultSet.getString("color"))
                    .size(resultSet.getString("size"))
                    .ownerId(resultSet.getInt("owner_id"))
                    .amount(resultSet.getInt("amount"))
                    .build();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return product;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        var connection = getConnection();
        String query = "select * from product";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()){
            Product product = Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getDouble("price"))
                    .subcategoryId(resultSet.getInt("subcategory_id"))
                    .description(resultSet.getString("description"))
                    .color(resultSet.getString("color"))
                    .size(resultSet.getString("size"))
                    .ownerId(resultSet.getInt("owner_id"))
                    .amount(resultSet.getInt("amount"))
                    .build();
            products.add(product);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return products;
    }
}
