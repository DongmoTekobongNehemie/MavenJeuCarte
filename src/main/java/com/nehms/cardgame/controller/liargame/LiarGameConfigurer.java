package com.nehms.cardgame.controller.liargame;

import com.nehms.cardgame.controller.Configurer;
import com.nehms.cardgame.model.Card;
import com.nehms.cardgame.model.Pattern;
import com.nehms.cardgame.model.Player;

import java.util.Collections;
import java.util.List;

public class LiarGameConfigurer implements Configurer {

    @Override
    public void createCards(List<Card> cards) {

        final String[] numberCards = { "A", "7", "8", "9", "10", "J", "Q", "K" };

        for (String numberCard : numberCards) {
            cards.add(new Card(Pattern.CARREAU, numberCard));
            cards.add(new Card(Pattern.TREFLE, numberCard));
            cards.add(new Card(Pattern.COEUR, numberCard));
            cards.add(new Card(Pattern.PIQUE, numberCard));
        }

    }

    @Override
    public void mixCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    @Override
    public void removeCards(Card card, List<Card> cards) {
        cards.removeIf(car -> car.getPattern().equals(card.getPattern()) && car.getNumber().equals(card.getNumber()));
    }

    @Override
    public void distribute(List<Card> cards, List<Player> players) {
        int nbrePartMain = cards.size() / players.size();
        for (Player  joueur : players) {
            for (int i = 0; i < nbrePartMain; i++) {
                int lastIndex = cards.size() - 1;
                joueur.getHand().add(cards.get(lastIndex));
                removeCards(joueur.getHand().get(i), cards);
            }
        }
    }
}
