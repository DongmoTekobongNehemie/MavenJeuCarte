package com.nehms.cardgame.controller;

import com.nehms.cardgame.model.Card;
import com.nehms.cardgame.model.Player;

import java.util.List;

public interface Configurer {

    void createCards(List<Card> cards);

    void mixCards(List<Card> cards);

    void removeCards(Card card, List<Card> cards);

    void distribute(List<Card> cards, List<Player> players);

}
