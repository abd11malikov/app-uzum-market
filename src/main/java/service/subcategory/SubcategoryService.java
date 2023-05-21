package service.subcategory;

import model.SubCategory;
import service.baseService.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface SubcategoryService extends BaseService<SubCategory> {
    SubCategory getByName (String name) throws SQLException;
    List<SubCategory> getSubcategories (int categoryId) throws SQLException;
}
