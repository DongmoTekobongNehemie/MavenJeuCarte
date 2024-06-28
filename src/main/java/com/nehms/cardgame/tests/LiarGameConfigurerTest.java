package com.nehms.cardgame.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.nehms.cardgame.controller.Configurer;
import com.nehms.cardgame.controller.liargame.LiarGameConfigurer;
import com.nehms.cardgame.model.Card;
import com.nehms.cardgame.model.Pattern;
import com.nehms.cardgame.model.Player;

class LiarGameConfigurerTest {

	private List<Card> cards;
	private Configurer liardGameConfigurer;
	private List<Player> players;

	@BeforeEach
	void setup() {

		liardGameConfigurer = new LiarGameConfigurer();

		players = new ArrayList<>();

		cards = new ArrayList<>();

		final String[] numberCards = { "A", "7", "8", "9", "10", "J", "Q", "K" };

		for (String numberCard : numberCards) {
			cards.add(new Card(Pattern.CARREAU, numberCard));
			cards.add(new Card(Pattern.TREFLE, numberCard));
			cards.add(new Card(Pattern.COEUR, numberCard));
			cards.add(new Card(Pattern.PIQUE, numberCard));
		}
		
		Player player1 = new Player("player_1");
		Player player2 = new Player("player_2");
		
		players.add(player1);
		players.add(player2);
		
	}

	// All cases of test on the method createCards
	@Test
	void testCreateCards() {
		cards.clear();
		liardGameConfigurer.createCards(cards);
		assertEquals(32, cards.size());
	}

	@Test
	@DisplayName("create Cards when the cards list is null")
	void testCreateCards1() {
		cards = null;
		assertThrows(NullPointerException.class,()->{
			liardGameConfigurer.createCards(cards);
		});
	}

	// All cases of test on the method mixCards
	@Test
	void testMixCards() {
		Card first = cards.getFirst();
		liardGameConfigurer.mixCards(cards);
			assertNotEquals(first.getNumber(), cards.getFirst().getNumber());
			assertNotEquals(first.getPattern(), cards.getFirst().getPattern());
	}

	@Test
	@DisplayName("mixCards when the cards list is null")
	void testMixCards1() {
		cards =null;
		assertThrows(NullPointerException.class,()->{
			liardGameConfigurer.mixCards(cards);
		});
	}

	// All cases of test on the method removeCards
	@Test
	void testRemoveCards() {
		Card card = cards.getFirst();
		liardGameConfigurer.removeCards(card, cards);
		assertTrue(!cards.contains(card));
	}

	@Test
	@DisplayName("removeCards when the cards list is null")
	void testRemoveCards1() {
		Card card = cards.getFirst();
		cards =null;
		assertThrows(NullPointerException.class,()->{
			liardGameConfigurer.removeCards(card, cards);
		});
	}
	
	@Test
	@DisplayName("removeCards when the cards is null")
	void testRemoveCards2() {
		Card card = null;
		assertThrows(NullPointerException.class,()->{
			liardGameConfigurer.removeCards(card, cards);
		});
	}
	
	@Test
	@DisplayName("removeCards when the cards it's not in the list cards")
	void testRemoveCards3() {
		Card card = cards.getFirst();
		cards.removeFirst();
		liardGameConfigurer.removeCards(card, cards);
		assertEquals(31, cards.size());
	}
	
	// All cases of test on the method removeCards
	@Test
	void testDistribute() {
		liardGameConfigurer.distribute(cards, players);
		assertEquals(players.get(1).getHand().size(), players.get(0).getHand().size());
	}
	
	@Test
	@DisplayName("Distribute when the cards it's null")
	void testDistribute1() {
		cards= null;
		assertThrows(NullPointerException.class, ()->{
			liardGameConfigurer.distribute(cards, players);
		});
	}
	
	@Test
	@DisplayName("Distribute when the players list it's null")
	void testDistribute2() {
		players = null;
		assertThrows(NullPointerException.class, ()-> liardGameConfigurer.distribute(cards, players)
		);
	}
	
}
