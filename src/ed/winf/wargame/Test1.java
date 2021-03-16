package ed.winf.wargame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test1 {

	public static void main(String[] args) throws FileNotFoundException {
		//Pang read nung text file
//		File file = new File("C:/Users/turin/Downloads/Card Sequence.txt");
//		Scanner scan = new Scanner(file);
		String cards = "D-A,D-K,D-Q,D-J,D-10,D-9,D-8,D-7,D-6,D-5,D-4,D-3,D-2,H-A,H-K,H-Q,H-J,H-10,H-9,H-8,H-7,H-6,H-5,H-4,H-3,H-2,S-A,S-K,S-Q,S-J,S-10,S-9,S-8,S-7,S-6,S-5,S-4,S-3,S-2,C-A,C-K,C-Q,C-J,C-10,C-9,C-8,C-7,C-6,C-5,C-4,C-3,C-2";
		
//		scan.close();
		
		//Seperator don sa Text file
		StringTokenizer stk = new StringTokenizer(cards, ",-");
		
		//Check mo if may iba pang way feeling mo mali 
		//how to seperate if string yung kasunod loop? 
		
		ArrayList<Card> deck = new ArrayList<Card>();
		
		int i = 0;
		Card card = null;
		while(stk.hasMoreTokens()) {
			
				
			
			if(i % 2 == 0) {
				card = new Card();
				String suit = stk.nextToken();
				card.suit = suit;
				System.out.println(suit);
			}
			else if(i % 2 == 1) { //odd
				String rank = stk.nextToken();
				System.out.println(rank);
				card.rank = rank;
				deck.add(card);
			}
			i++;
		}
		System.out.println("No more tokens");
		for (int j=0;j<deck.size();j++) {
			Card card2 = deck.get(j);
			System.out.println("card " + j + ": " + card2.suit + card2.rank + "points" + card2.getRank());
		}
			
	}	
}
