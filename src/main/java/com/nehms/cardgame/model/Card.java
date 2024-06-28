package com.nehms.cardgame.model;

import java.util.Objects;

public class Card {
	
	private Pattern pattern;
	private String number;

	public Card(Pattern pattern, String number) {
		this.pattern = pattern;
		this.number = number;
	}
	
	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Carte [Nom=" + pattern + ", numCarte=" + number + "]";
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Card card)) return false;

        return pattern == card.pattern && Objects.equals(number, card.number);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(pattern);
		result = 31 * result + Objects.hashCode(number);
		return result;
	}
}
