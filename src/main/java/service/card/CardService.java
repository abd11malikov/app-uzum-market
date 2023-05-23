package service.card;

import model.Card;
import service.baseService.BaseService;

import java.sql.SQLException;

public interface CardService extends BaseService<Card> {
    boolean addSum(double price, int ownerCardId,int userCardId) throws SQLException;
    int getOwnerCard (int ownerId) throws SQLException;
    int checkCard(int userId) throws SQLException;
}
