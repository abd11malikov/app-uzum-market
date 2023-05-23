import model.Card;
import model.User;
import org.postgresql.core.BaseConnection;
import service.baseService.BaseService;
import service.card.CardService;

import java.security.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class TestMain  {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/uzum_market";
        String name = "postgres";
        String password = "12345678";




//        var connection = DriverManager.getConnection(url, name, password);
//        String query = "select check_product(?, ?)";
//        var preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1,2);
//        preparedStatement.setInt(2, 2);
//        var resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()) {
//            System.out.println(resultSet.getBoolean(1));
//        }
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();

    }
}
