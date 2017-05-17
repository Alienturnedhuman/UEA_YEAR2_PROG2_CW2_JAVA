/*
 * Coursework 2 - Java Card Game
 */
package question2;

import java.util.Random;

/**
 * My Strategy
 * On each streak (defined between calls of cheat) the AI randomly decides
 * if it will or won't cheat for that run (50/50)
 * 
 * if it does, then it will be more likely to cheat if the discard pile
 * is small
 * 
 * it uses a similar basic premise to ThinkerStrategy for called cheat, keeping
 * track of the cards it has played and the size ofthe pile, however it has
 * a sense variable, that adjusts the threashold for calling cheat based on
 * its successs. The more successful it is, it will keep reducing it, however
 * if it starts to fail, it will increase it again.
 * 
 * @author Ben Dickson
 */
public class MyStrategy implements Strategy {
    private Random random=new Random();
    
    private Hand inDiscard = new Hand();
    int discardSize=0;
    
    double sense=0.8;
    int handSizeBeforeCalling=0;
    
    boolean dontCheat = random.nextInt(2)==0;
    int discardOnCalling=0;
    
    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank nextRank = b.getRank().getNext();
        // cheats if it has to, or randomly if cheating allowed this bid
        // with it more likely to cheat with a smaller discard pile)
        if(
            h.countRank(nextRank)>0
            &&
            (
                dontCheat
                ||
                random.nextInt(52)>(discardSize+h.size())
            )
        )
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
                if(h.contains(c))
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
    /*
        My call cheat system is to have a self leveling probability multiplier
        That adjusts its limit based on previous success or failure
        If it's successful, it will continue to drop the threshold until it
        fails, and then it will raise it
    */
    @Override
    public boolean callCheat(Hand h, Bid b) {
        int bidSize = b.getHand().size();
        if(bidSize<1)
        {
            // adjusts its sense limit for calling cheat based on success
            if(discardSize==discardOnCalling)
            {
                if(handSizeBeforeCalling==h.size())
                {
                    sense=sense/1.05;
                }
                else
                {
                    sense=sense*1.05;
                }
            }
            inDiscard = new Hand();
            discardSize=0;
            // will randomly choose not to call cheat each discard run
            // (to fool human players)
            dontCheat = random.nextInt(2)==0;
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
                if(discardSize<6)
                {
                    return false;
                }
                int unaccounted = 4 - accounted;
                double pFactor = 0.8*Math.pow(sense,unaccounted-bidSize);
                double cFactor = ((double)h.size()+(double)discardSize)/52;
                if((pFactor*cFactor)>0.5)
                {
                    handSizeBeforeCalling=h.size();
                    discardOnCalling=discardSize;
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
