package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface BaseService <T>{
    default Connection getConnection () throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/uzum_market";
        String name = "postgres";
        String password = "12345678";
        return DriverManager.getConnection(url,name,password);
    }
    int create (T t) throws SQLException;
    boolean delete (int id);
    T get(int id);
    List<T> getALl ();
}
