package ed.winf.wargame;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	
	public void shuffle(int limit) {
		int shuffleCount = 0;
		ArrayList<Card> shuffledDeck = null;
		ArrayList<Card> refDeck = deck;
		while(shuffleCount < limit){
			shuffledDeck = new ArrayList<Card>();
			int half = deck.size() / 2;
			ArrayList<Card> deck1 = new ArrayList<Card>(refDeck.subList(0, half));
			ArrayList<Card> deck2 = new ArrayList<Card>(refDeck.subList(half, deck.size()));
			int i = 0;
			while(i < half) {
				shuffledDeck.add(deck1.get(i));
				shuffledDeck.add(deck2.get(i));
				i++;
			}
			refDeck = shuffledDeck;
			shuffleCount++;
		};
		this.deck = shuffledDeck;
	}
	
	public Card draw() {
		Card card = deck.get(deck.size()-1);
		deck.remove(deck.size()-1);
		return card;
	}
	
	public void insertToBottom(Card card) {
		deck.add(0, card);
	}
	
	public void insert(Card card) {
		deck.add(card);
	}
	
	public void printCards() {
		System.out.println("deck size " + deck.size());
		for(int i=0; i < deck.size(); i++) {
			Card card = deck.get(i);
			System.out.println((i+1)+":" + card);
		}
	}
	
	public void printCardsComma() {
		System.out.println("deck size " + deck.size());
		for(int i=0; i < deck.size(); i++) {
			Card card = deck.get(i);
			System.out.print(card + ",");
		}
		System.out.println();
	}
	
	public int size() {
		return deck.size();
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
}
