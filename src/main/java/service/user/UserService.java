package service.user;

import dto.HistoryResponse;
import model.User;
import service.baseService.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends BaseService<User> {
      boolean addToBasket(int userId, int productId) throws SQLException;
      User getByPhone (String phone) throws SQLException;
     List<HistoryResponse> getHistories(int userId) throws SQLException;
}
