package service.user;

import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImp implements UserService {
    @Override
    public int create(User user) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getALl() {
        return null;
    }
}
