package service.user;

import enums.Gender;
import enums.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserService {
    @Override
    public int create(User user) throws SQLException {
        Connection connection = getConnection();
        String query = "select add_user(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPhoneNumber());
        preparedStatement.setString(3, user.getRole().name());
        preparedStatement.setString(4,user.getGender().name());
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
        String query = "delete from users where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public User get(int id) throws SQLException {
        var connnection = getConnection();
        String query = "select * from user where id = ?";
        PreparedStatement preparedStatement = connnection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if(resultSet.next()){
            user = User.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .phoneNumber(resultSet.getString("phone_number"))
                    .role(Role.valueOf(resultSet.getString("role")))
                    .gender(Gender.valueOf(resultSet.getString("gender")))
                    .build();
        }
        resultSet.close();
        preparedStatement.close();
        connnection.close();
        return user;
    }

    @Override
    public List<User> getALl() throws SQLException {
        var connection = getConnection();
        String query = "select * from users";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()){
            User user = User.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .phoneNumber(resultSet.getString("phone_number"))
                    .role(Role.valueOf(resultSet.getString("role")))
                    .gender(Gender.valueOf((resultSet.getString("gender"))))
                    .build();
            users.add(user);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return users;
    }
}
