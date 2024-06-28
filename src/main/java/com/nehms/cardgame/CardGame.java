package com.nehms.cardgame;

import com.nehms.cardgame.controller.Playable;
import com.nehms.cardgame.controller.liargame.LiarGamePlay;
import com.nehms.cardgame.exceptions.EmptyListOfCardsException;

import java.util.logging.Logger;

public class CardGame {

	private static final Logger logger = Logger.getLogger("CardGame");

	public static void main(String[] args) {
		try {
			Playable liar = new LiarGamePlay();
			liar.play();
		} catch (EmptyListOfCardsException e) {
			logger.severe(e.getMessage());
		}

	}

}
