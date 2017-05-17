/*
 * Coursework 2 - Java Card Game
 */
package question2;

import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Ben Dickson
 */
public class HumanStrategy implements Strategy {
    
    
    Scanner in=new Scanner(System.in);
    
    @Override
    public boolean cheat(Bid b, Hand h) {
        return true;
        // irrelevant as HUman will decide so just return true
    }
    
    
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Card.Rank nextRank = b.getRank().getNext();
        Hand rH = new Hand();
        String ui="";
        
        // user enters card code (eg 5H or AS) or leaves blank to complete
        while(rH.size()==0||!ui.equals(""))
        {
            System.out.println("You need to play a card of value: "
                    + nextRank.name);
            System.out.println("Select card from hand or leave blank to end:");
            System.out.println(h.toString(true));
            ui = in.nextLine();
            boolean isCard=false;
            
            Card mc = null;
            for(Iterator<Card> it = h.iterator();!isCard&&it.hasNext();)
            {
                Card c=it.next();
                if(ui.equals(c.toString(true)))
                {
                    isCard=true;
                    mc=c;
                }
            }
            if(isCard)
            {
                h.remove(mc);
                rH.add(mc);
                
            }
            else if(!ui.equals("")||rH.size()==0)
            {
                System.out.println("\nInvalid card, please select another");
            }
        }
        
        return new Bid(rH,nextRank);
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        // if hand size = 0 then this means that it's just updating cheat info
        // for AIs so return on this without prompting.
        if(b.getHand().size()<1)
        {
            return false;
        }
        String ui="";
        System.out.println("\nYour hand is:");
        System.out.println(h.toString(true));
        while(
                !ui.equals("Y")
                &&
                !ui.equals("y")
                &&
                !ui.equals("N")
                &&
                !ui.equals("n")
        )
        {
            System.out.println("Call cheat (Y/N)?"); 
            ui = in.nextLine();
        }
        
        return ui.equals("Y")||ui.equals("y");
    }
    
}
