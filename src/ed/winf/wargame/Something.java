package ed.winf.wargame;
import java.util.*;
import java.lang.NumberFormatException;

public class Something {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int playerCount = 0;
		int minPlayerCount = 2;
		int maxPlayerCount = 10;
		do {
			System.out.print("Input number of players: ");
			try {
				playerCount = Integer.parseInt(scanner.nextLine());				
			} catch (Exception e) {
				System.out.println("please input nubmers 2-10");
			}
		}while(playerCount < minPlayerCount || playerCount > maxPlayerCount);
		System.out.println("player count is : " + playerCount);
	}
}
