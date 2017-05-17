/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;


/**
 * BasicStrategy - implementation of strategy described in coursework document
 * @author Ben Dickson
 */
public class BasicStrategy implements Strategy {

    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank nextRank = b.getRank().getNext();
        if(h.countRank(nextRank)>0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        // get the next rank
        Card.Rank nextRank = b.getRank().getNext();
        
        // if not cheating:
        if(!cheat)
        {
            // create a return hand
            Hand bidHand = new Hand();
            
            // loop through each suit and if that rank exists, add to hand
            Card.Suit[] suitList = Card.Suit.values();
            int ss = suitList.length;
            int s = -1;
            while(++s<ss)
            {
                Card c = new Card(suitList[s],nextRank);
                if(h.contains(c))
                {
                    bidHand.add(c);
                }
            }
            // double check it definintely got cards, if not fall back to cheat
            if(bidHand.size()>0)
            {
                // remove bidHand from AI hand
                h.remove(bidHand);
                
                // return hand
                return new Bid(bidHand,nextRank);
            }
        }
        
        // if cheating, return a random card
        Hand bidHand = new Hand(new Card[]{h.takeRandomCard()});
        return new Bid(bidHand,nextRank);
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        Card.Rank bidRank = b.getRank();
        
        // add together number of bid cards with number of known cards in hand
        int totalRankCards = b.getHand().size() + h.countRank(bidRank);
        
        // if >4 then player must be cheating
        if(totalRankCards>4)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
