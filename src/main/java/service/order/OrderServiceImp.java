package service.order;

import model.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImp implements OrderService{
    @Override
    public int create(Order order) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public List<Order> getALl() {
        return null;
    }
}
