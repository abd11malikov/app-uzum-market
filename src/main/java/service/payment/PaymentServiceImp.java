package service.payment;

import model.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImp implements PaymentService{
    @Override
    public int create(Payment payment) throws SQLException {
        var connection = getConnection();
        String query = "select add_payment(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,payment.getCardId());
        preparedStatement.setDouble(2,payment.getPrice());
        preparedStatement.setInt(3,payment.getOrderId());
        ResultSet resultSet = preparedStatement.executeQuery();
        var result = -1;
        if(resultSet.next()){
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
        String query = "delete from payment where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public Payment get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select from payment where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if(resultSet.next()){
            payment = Payment.builder()
                    .id(resultSet.getInt("id"))
                    .cardId(resultSet.getInt("card_id"))
                    .price(resultSet.getDouble("price"))
                    .createdDate(resultSet.getTimestamp("created_date"))
                    .orderId(resultSet.getInt("order_id"))
                    .build();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return payment;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        var connection = getConnection();
        String query = "select * from payment";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()){
            Payment payment = Payment.builder()
                    .id(resultSet.getInt("id"))
                    .cardId(resultSet.getInt("card_id"))
                    .price(resultSet.getDouble("price"))
                    .createdDate(resultSet.getTimestamp("created_date"))
                    .orderId(resultSet.getInt("order_id"))
                    .build();
            payments.add(payment);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return payments;
    }
}
