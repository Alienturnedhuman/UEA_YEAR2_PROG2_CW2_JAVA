/*
 * Coursework 2 - Java Card Game
 */
package question2;

import java.util.Random;

/**
 * Immplementation of the ThinkerStrategy described in the coursework
 * @author Ben Dickson
 */
public class ThinkerStrategy implements Strategy {
    // used for generating random results
    private Random random=new Random();
    
    // used for keeping track of known cards in discard pile
    private Hand inDiscard = new Hand();
    
    // used for keeping track of the size of the discard pile
    int discardSize=0;
    
    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank nextRank = b.getRank().getNext();
        // cheats if forced to, or 25% of the time it doesn't
        if(h.countRank(nextRank)>0&&random.nextInt(16)<12)
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
        Card.Rank nextRank = b.getRank().getNext();
        if(!cheat)
        {
            Hand bidHand = new Hand();
            
            Card.Suit[] suitList = Card.Suit.values();
            int ss = suitList.length;
            int s = -1;
            while(++s<ss)
            {
                Card c = new Card(suitList[s],nextRank);
                // will always add a card if empty
                // but only 75% of the time for each subsequent available card
                if(h.contains(c)&&(bidHand.size()==0||random.nextInt(16)<12))
                {
                    bidHand.add(c);
                }
            }
            if(bidHand.size()>0)
            {
                h.remove(bidHand);
                inDiscard.add(bidHand);
                discardSize+=bidHand.size();
                return new Bid(bidHand,nextRank);
            }
        }
        h.sortDescending();
        Hand bidHand = new Hand(new Card[]{h.takeCardFromPos(0)});
        discardSize+=bidHand.size();
        return new Bid(bidHand,nextRank);
    }
    
    
    @Override
    public boolean callCheat(Hand h, Bid b) {
        int bidSize = b.getHand().size();
        if(bidSize<1)
        {
            inDiscard = new Hand();
            discardSize=0;
            return false;
        }
        else
        {
            discardSize += bidSize;
            Card.Rank bidRank = b.getRank();
            int accounted = h.countRank(bidRank) + inDiscard.countRank(bidRank);
            int totalRankCards = bidSize + accounted;
            if(totalRankCards>4)
            {
                return true;    // certain of cheating
            }
            else
            {
                // determines approximate guess of whether it's a cheat
                // based on number of cards in discard pile and the size
                // of the bid
                int unaccounted = 4 - accounted;
                double pFactor = Math.pow(0.8,1+unaccounted-bidSize);
                double cFactor = ((double)h.size()+(double)discardSize)/52;
                if((pFactor*cFactor)>0.5)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }
    
}
