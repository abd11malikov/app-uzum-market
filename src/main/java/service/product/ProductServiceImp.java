package service.product;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImp implements ProductService{
    @Override
    public int create(Product product) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Product get(int id) {
        return null;
    }

    @Override
    public List<Product> getALl() {
        return null;
    }
}
