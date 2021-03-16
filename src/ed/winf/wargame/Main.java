package ed.winf.wargame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//Pang read nung text file
// 		File file = new File("C:/Users/turin/Downloads/Card Sequence.txt");
// 		Scanner scan = new Scanner(file);
        
		String cardsStr = "D-A,D-K,D-Q,D-J,D-10,D-9,D-8,D-7,D-6,D-5,D-4,D-3,D-2,H-A,H-K,H-Q,H-J,H-10,H-9,H-8,H-7,H-6,H-5,H-4,H-3,H-2,S-A,S-K,S-Q,S-J,S-10,S-9,S-8,S-7,S-6,S-5,S-4,S-3,S-2,C-A,C-K,C-Q,C-J,C-10,C-9,C-8,C-7,C-6,C-5,C-4,C-3,C-2";
// 		scan.close();
		
		//Seperator don sa Text file
		StringTokenizer stk = new StringTokenizer(cardsStr, ",");
		ArrayList<Card> deck = new ArrayList<Card>();
		while(stk.hasMoreTokens()) {
			String token = stk.nextToken(); //D-A
//			System.out.println(token);
			Card card = new Card();
			String[] s = token.split("-"); // ["D" , "A"]
		
			card.suit = s[0];
			card.rank = s[1];
			deck.add(card);
		}
		//output cards
//		System.out.println("deck size " + deck.size());
//		for(int i=0; i < deck.size(); i++) {
//			Card card = deck.get(i);
//			System.out.println(card.suit + " " + card.rank);
//		}
		
		Scanner scanner = new Scanner(System.in);
		int playerCount = 0;
		int shuffleLimit = 0;
		System.out.print("Input number of players: ");
		playerCount = Integer.parseInt(scanner.nextLine());
		System.out.print("Input number of shuffle: ");
		shuffleLimit = Integer.parseInt(scanner.nextLine());
		
		int shuffleCount = 0;
		ArrayList<Card> shuffledDeck = null;
		ArrayList<Card> refDeck = deck;
		while(shuffleCount < shuffleLimit){
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
		
		//output cards
		System.out.println("deck size " + shuffledDeck.size());
		for(int i=0; i < shuffledDeck.size(); i++) {
			Card card = shuffledDeck.get(i);
			System.out.println((i+1)+":" + card.suit + " " + card.rank);
		}
		ArrayList<ArrayList<Card>> playerCards = new ArrayList<ArrayList<Card>>();
		//initialize deck
		for(int i=0; i < playerCount; i++) {
			playerCards.add(new ArrayList<Card>());
		}
//		int player = 0;
		for(int i=0; i < shuffledDeck.size(); i++) {
			Card card = shuffledDeck.get(i);
			int player = i % playerCount;
			
			System.out.println("giving" + (i+1)+":" + card.suit + " " + card.rank + " to player " + player);
			ArrayList<Card> playerDeck = playerCards.get(player);
			playerDeck.add(card);
			playerCards.set(player, playerDeck);
			player++;
//			if (player == playerCount) {
//				player = 0;
//			}
		}
		//battle time here
		int round = 0;
		int playerWithCards = 0;
		do {
			playerWithCards = 0;
			int highestPlayerNumber = -1; 
			Card highestCard = null;
			System.out.println("round " + round);
			Card[] tableCards = new Card[playerCount];
			for(int i=0; i < playerCount; i++) {
				ArrayList<Card> playerDeck = playerCards.get(i);
				if (playerDeck.isEmpty()) {
					continue;
				}
				playerWithCards++;
				Card playerCard = playerDeck.get(0);
				tableCards[i] = playerCard;
				System.out.println("player " + i + ": " + playerCard);
				if (highestCard == null) {
					highestCard = playerCard;
					highestPlayerNumber = i;
				} else {
					if (playerCard.getRank() > highestCard.getRank()) {
						highestPlayerNumber = i;
						highestCard = playerCard;
					}
				}
				
			}
			
			System.out.println("player " + highestPlayerNumber + "wins with card" + highestCard.suit + highestCard.rank);
			for(int i=0; i < playerCount; i++) {
				ArrayList<Card> playerDeck = playerCards.get(i);
				if (!playerDeck.isEmpty()) {
					playerDeck.remove(0);					
				}
			}
			for(int i=0; i < playerCount; i++) {
				Card card = tableCards[i];
				if (card != null ) {
					playerCards.get(highestPlayerNumber).add(card);
				}
				
			}
			round++;
			for (int i=0; i< playerCount; i++) {
				ArrayList<Card> playerDeck = playerCards.get(i);
				System.out.println("player " + i + "deck:");
				for (Card card : playerDeck) {
					System.out.print(card + ",");
				}
				System.out.println();
			}
		} while (playerWithCards > 1);
		
	}	
}