package service.category;

import model.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImp implements CategoryService{
    @Override
    public int create(Category category) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Category get(int id) {
        return null;
    }

    @Override
    public List<Category> getALl() {
        return null;
    }
}
