package enums;



public enum CardType {
    HUMO,
    UZCARD,
    VISA;

    public CardType cardType (String cardType) {
        for (CardType cardType1 : values()) {
            if (cardType1.name().equals(cardType)) {
                return cardType1;
            }
        }
        return null;

    }

}