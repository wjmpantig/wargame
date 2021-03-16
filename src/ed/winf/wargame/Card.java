package ed.winf.wargame;

public class Card {
	public String suit;
    public String rank = null;
    
    public int getRank() {
        String[] rankOrder = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for(int i=0; i<rankOrder.length; i++) {
        	if (rankOrder[i].equals(this.rank)) {
        		return i;
        	}
        }
        return -1;
    }
    
    public String toString() {
    	return suit + rank;
    }
}
