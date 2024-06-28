package com.nehms.cardgame.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.nehms.cardgame.controller.Playable;
import com.nehms.cardgame.controller.liargame.LiarGamePlay;
import com.nehms.cardgame.model.Card;
import com.nehms.cardgame.model.Pattern;
import com.nehms.cardgame.model.Player;

class LiarGamePlayTest {

	private Playable liardGamePlay;
	private List<Card> cardsPlayed;
	private List<Player> players;

	@BeforeEach
	void setup() {
		liardGamePlay = new LiarGamePlay();
		cardsPlayed = new ArrayList<>();
		players = new ArrayList<>();
		players.add(new Player("player1"));
		players.add(new Player("player2"));
	}

	@Test
	void testPlay() {
		assertDoesNotThrow(() -> liardGamePlay.play());
	}

	@Test
	void testPlayOneCard() {
		Card currentCard = new Card(Pattern.PIQUE, "J");
		Player player = new Player("player");
		player.getHand().add(currentCard);
		liardGamePlay.playOneCard(player, cardsPlayed, currentCard, Pattern.CARREAU);
		assertTrue(!cardsPlayed.isEmpty());
	}

	@Test
	@DisplayName("playedOneCards with null player")
	void testPlayOneCard1() {
		Card currentCard = new Card(Pattern.PIQUE, "J");
		Player player = null;
		assertThrows(NullPointerException.class,
				() -> liardGamePlay.playOneCard(player, cardsPlayed, currentCard, Pattern.TREFLE));
	}

	@Test
	@DisplayName("playedOneCards with null players list")
	void testPlayOneCard2() {
		Card currentCard = new Card(Pattern.PIQUE, "J");
		Player player = new Player("player");
		player.getHand().add(currentCard);
		cardsPlayed = null;
		assertThrows(NullPointerException.class,
				() -> liardGamePlay.playOneCard(player, cardsPlayed, currentCard, Pattern.CARREAU));
	}

	@Test
	@DisplayName("playedOneCards with null currentCard")
	void testPlayOneCard3() {
		Card currentCard = null;
		Player player = new Player("player");
		player.getHand().add(currentCard);
		cardsPlayed = null;
		assertThrows(NullPointerException.class,
				() -> liardGamePlay.playOneCard(player, cardsPlayed, currentCard, Pattern.PIQUE));
	}

	@Test
	void testContradict() {
		Card currentCard = new Card(Pattern.PIQUE, "J");
		Pattern pattern = Pattern.CARREAU;
		cardsPlayed.add(currentCard);
		LiarGamePlay liardGamePlay = new LiarGamePlay();
		liardGamePlay.contradict(players.get(0), players, currentCard, pattern, cardsPlayed);
		int size1 = players.get(0).getHand().size();
		int size2 = players.get(1).getHand().size();
		assertNotEquals(size1, size2);
	}

	@Test
	@DisplayName("Contradict with null currentCard")
	void testContradict1() {

		Card currentCard = null;
		Pattern pattern = Pattern.CARREAU;
		cardsPlayed.add(currentCard);
		LiarGamePlay liardGamePlay = new LiarGamePlay();
		assertThrows(NullPointerException.class,
				() -> liardGamePlay.contradict(players.get(0), players, currentCard, pattern, cardsPlayed));

	}

//hum
	@Test
	@DisplayName("Contradict with null pattern")
	void testContradict2() {
		Card currentCard = new Card(Pattern.PIQUE, "J");
		Pattern pattern = null;
		cardsPlayed.add(currentCard);
		LiarGamePlay liardGamePlay = new LiarGamePlay();
		liardGamePlay.contradict(players.get(0), players, currentCard, pattern, cardsPlayed);
		int size1 = players.get(0).getHand().size();
		int size2 = players.get(1).getHand().size();
		assertNotEquals(size1, size2);
	}

}
