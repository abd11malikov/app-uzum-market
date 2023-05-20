package service.card;

import model.Card;

import java.sql.SQLException;
import java.util.List;

public class CardServiceImp implements CardService{
    @Override
    public int create(Card card) throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Card get(int id) {
        return null;
    }


    @Override
    public List<Card> getALl() {
        return null;
    }
}
