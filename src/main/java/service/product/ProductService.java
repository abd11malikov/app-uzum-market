package service.product;

import model.Category;
import model.Product;
import service.baseService.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends BaseService<Product> {
    boolean orderProducts(int orderId, int productId) throws SQLException;
    boolean checkProduct(int productId, int userId) throws SQLException;
    List<Product> getSubcategoryProducts(int subcategoryId) throws SQLException;

}
