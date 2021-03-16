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
        // hardcoded deck;
		String cardsStr = "D-A,D-K,D-Q,D-J,D-10,D-9,D-8,D-7,D-6,D-5,D-4,D-3,D-2,H-A,H-K,H-Q,H-J,H-10,H-9,H-8,H-7,H-6,H-5,H-4,H-3,H-2,S-A,S-K,S-Q,S-J,S-10,S-9,S-8,S-7,S-6,S-5,S-4,S-3,S-2,C-A,C-K,C-Q,C-J,C-10,C-9,C-8,C-7,C-6,C-5,C-4,C-3,C-2, ,D-123,A2,C1";
// 		scan.close();
		
		//Seperator don sa Text file
		StringTokenizer stk = new StringTokenizer(cardsStr, ","); //split the string with token comma
		Deck deck = new Deck();
		while(stk.hasMoreTokens()) { //iterate tokenized strings
			String token = stk.nextToken(); //D-A
//			System.out.println(token);
			if (token.length() < 3 || token.length() > 4) {
				System.out.println("skipping invalid input: " + token);
				continue;
			}
			Card card = new Card();
			String[] s = token.split("-"); // ["D" , "A"]
		
			card.suit = s[0];
			card.rank = s[1];
			if (card.getRank() == -1 || card.getSuitRank() == -1) {
				System.out.println("skipping invalid input: " + token);
				continue;
			}
			deck.insert(card);
		}
		deck.printCards();
		
		Scanner scanner = new Scanner(System.in);
		int playerCount = 0;
		int shuffleLimit = 0;
		
		System.out.print("Input number of players: "); //TODO: catch invalid input
		playerCount = Integer.parseInt(scanner.nextLine());
		System.out.print("Input number of shuffle: ");
		shuffleLimit = Integer.parseInt(scanner.nextLine());
		
		deck.shuffle(shuffleLimit);
		
		//output cards
		deck.printCards();
		
		ArrayList<Player> players = new ArrayList<Player>();
		//initialize deck
		for(int i=0; i < playerCount; i++) {
			players.add(new Player());
		}
		for(int i = deck.size() - 1; i >= 0; i--) {
			Card card = deck.draw();
			int player = i % playerCount;
			
			System.out.println("giving" + (i+1)+":" + card.suit + " " + card.rank + " to player " + player);
			Deck playerDeck = players.get(player).getDeck();
			playerDeck.insert(card);
			player++;
		}
		//battle time here
		int round = 0;
		int playerWithCards = 0;
		int highestPlayerNumber = -1; 
		do {
			playerWithCards = 0;
			Card highestCard = null;
			System.out.println("round " + round);
			Card[] tableCards = new Card[playerCount];
			for(int i=0; i < playerCount; i++) {
				Deck playerDeck = players.get(i).getDeck();
				if (playerDeck.isEmpty()) {
					continue;
				}
				playerWithCards++;
				Card playerCard = playerDeck.draw();
				tableCards[i] = playerCard;
				System.out.println("player " + i + ": " + playerCard);
				if (highestCard == null) {
					highestCard = playerCard;
					highestPlayerNumber = i;
				} else {
					if (playerCard.getRank() > highestCard.getRank()) {
						highestPlayerNumber = i;
						highestCard = playerCard;
					} else if (playerCard.getRank() == highestCard.getRank() &&
							playerCard.getSuitRank() > highestCard.getSuitRank()) {
						highestPlayerNumber = i;
						highestCard = playerCard;
					}
				}
				
			}
			
			System.out.println("player " + highestPlayerNumber + " wins with card " + highestCard);
			players.get(highestPlayerNumber).getDeck().insertToBottom(highestCard); //add highest card first
			for(int i=0; i < playerCount; i++) {
				Card card = tableCards[i];
				
				if (card != null && !highestCard.equals(card)) {
					System.out.println("give " + card + "to player" + highestPlayerNumber);
					players.get(highestPlayerNumber).getDeck().insertToBottom(card);
				}
			}
			round++;
			for (int i=0; i< playerCount; i++) {
				Deck playerDeck = players.get(i).getDeck();
				System.out.println("player " + i + "deck:");
				playerDeck.printCardsComma();
			}
		} while (playerWithCards > 1);
		System.out.println("winner is player " + highestPlayerNumber);
		
	}	
}