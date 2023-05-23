package service.order;

import model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImp implements OrderService{
    @Override
    public int create(Order order) throws SQLException {
        var connection = getConnection();
        String query = "select add_order(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,order.getUserId());
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
        String query = "select delete_order where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public Order get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select * from orders where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        Order order = null;
        if(resultSet.next()){
            order = Order.builder()
                    .id(resultSet.getInt("id"))
                    .userId(resultSet.getInt("user_id"))
                    .productId((List<Integer>) resultSet.getArray("order_product"))
                    .build();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return order;
    }

    @Override
    public List<Order> getALl() throws SQLException {
        var connection = getConnection();
        String query = "select * from orders";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()){
            Order order = Order.builder()
                    .id(resultSet.getInt("id"))
                    .userId(resultSet.getInt("user_id"))
                    .build();
            PreparedStatement preparedStatement1 = connection.prepareStatement("select product_id from order_product where id = ?");
            preparedStatement1.setInt(1,order.getId());
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            order.setProductId(new ArrayList<>());
            while (resultSet1.next()){
                order.getProductId().add(resultSet1.getInt(1));
            }
            orders.add(order);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return orders;
    }

    @Override
    public boolean orderProducts(int orderId, int productId) throws SQLException {
        var connection = getConnection();
        String query = "select add_order_product(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, orderId);
        preparedStatement.setInt(2, productId);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }
}
