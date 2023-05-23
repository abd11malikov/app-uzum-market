package service.category;

import model.Category;
import service.baseService.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService extends BaseService<Category> {
    Category getCategoryByName (String name) throws SQLException;

}
