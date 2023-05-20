package service.card;

import enums.CardType;
import model.Card;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardServiceImp implements CardService{
    @Override
    public int create(Card card) throws SQLException {
        var connection = getConnection();
        String query = "select add_card(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,card.getNumber());
        preparedStatement.setString(2,card.getType().name());
        preparedStatement.setInt(3,card.getOwnerId());
        preparedStatement.setString(4,card.getPassword());
        preparedStatement.setDouble(5,card.getBalance());
        preparedStatement.setDate(6, (Date) card.getDate());
        ResultSet resultSet = preparedStatement.executeQuery();
        int result = -1;
        if(resultSet.next()){
            result = resultSet.getInt(1);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        var connection = getConnection();
        String query = "delete from card where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public Card get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select from card where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Card card = null;
        if(resultSet.next()){
            card = Card.builder()
                    .id(resultSet.getInt("id"))
                    .number(resultSet.getString("number"))
                    .type(CardType.valueOf(resultSet.getString("card_type")))
                    .ownerId(resultSet.getInt("owner_id"))
                    .password(resultSet.getString("password"))
                    .balance(resultSet.getDouble("balance"))
                    .date(resultSet.getDate("date"))
                    .build();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return card;
    }


    @Override
    public List<Card> getAll() throws SQLException {
        var connection = getConnection();
        String query = "select * from card";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Card> cards = new ArrayList<>();
        while (resultSet.next()){
            Card card = Card.builder()
                    .id(resultSet.getInt("id"))
                    .number(resultSet.getString("number"))
                    .type(CardType.valueOf(resultSet.getString("card_type")))
                    .ownerId(resultSet.getInt("owner_id"))
                    .password(resultSet.getString("password"))
                    .balance(resultSet.getDouble("balance"))
                    .date(resultSet.getDate("date"))
                    .build();
            cards.add(card);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return cards;
    }
}
