package com.nehms.cardgame.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.nehms.cardgame.controller.PlayerCreator;
import com.nehms.cardgame.controller.liargame.LiarGamePlayerCreator;
import com.nehms.cardgame.model.Player;

class LiarGamePlayerCreatorTest {

	private PlayerCreator liardGameCreator;
	private List<Player> players;

	@BeforeEach
	void setup() {

		liardGameCreator = new LiarGamePlayerCreator();
		players = new ArrayList<>();
	}

	@Test
	void testCreate() {
		liardGameCreator.create(players);
	}

	// create when players list is null
	@Test
	@DisplayName("create when players list is null")
	void testCreate1() {
		players = null;
		assertThrows(NullPointerException.class, () -> liardGameCreator.create(players));
	}
}
