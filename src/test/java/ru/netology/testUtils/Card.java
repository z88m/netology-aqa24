package ru.netology.testUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    private String number;
    private String viewedNum;
    private int balance;

    public static void transferFromCardToCard(Card fromCard, Card toCard, int amount) {
        fromCard.setBalance(fromCard.getBalance() - amount);
        toCard.setBalance(toCard.getBalance() + amount);
    }
}