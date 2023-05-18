package service.subcategory;

import model.SubCategory;

import java.sql.SQLException;
import java.util.List;

public class SubcategoryServiceImp implements SubcategoryService{
    @Override
    public int create(SubCategory subCategory) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public SubCategory get(int id) {
        return null;
    }

    @Override
    public List<SubCategory> getALl() {
        return null;
    }
}
