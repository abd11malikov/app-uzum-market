package service.order;

import model.Order;
import service.baseService.BaseService;

import java.sql.SQLException;

public interface OrderService extends BaseService<Order> {
    public boolean orderProducts (int orderId, int productId) throws SQLException;
}
