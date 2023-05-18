package service.payment;

import model.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentServiceImp implements PaymentService{
    @Override
    public int create(Payment payment) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Payment get(int id) {
        return null;
    }

    @Override
    public List<Payment> getALl() {
        return null;
    }
}
