package com.nehms.cardgame.controller;

import java.util.List;

import com.nehms.cardgame.model.Card;
import com.nehms.cardgame.model.Pattern;
import com.nehms.cardgame.model.Player;
import com.nehms.cardgame.exceptions.EmptyListOfCardsException;

public interface Playable {

	void play() throws EmptyListOfCardsException;

	void playOneCard(Player joueur, List<Card> cardsPlayed, Card currentCard, Pattern pattern);
}
