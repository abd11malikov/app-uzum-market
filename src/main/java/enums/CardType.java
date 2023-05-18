package enums;



public enum CardType {
    XUMO,
    UZCARD,
    VIZA;

    public CardType cardType (String cardType) {
        for (CardType cardType1 : values()) {
            if (cardType1.name().equals(cardType)) {
                return cardType1;
            }
        }
        return null;

    }

}