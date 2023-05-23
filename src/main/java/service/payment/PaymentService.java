package service.payment;

import model.Payment;
import service.baseService.BaseService;

import java.sql.SQLException;

public interface PaymentService extends BaseService<Payment> {
    boolean addToUserHistory (int userId, int paymentId, int productId) throws SQLException;
}
