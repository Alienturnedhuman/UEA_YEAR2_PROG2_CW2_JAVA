/*
 * Coursework 2 - Java Card Game
 */
package question1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Ben Dickson
 */
public class Deck implements Iterable<Card> {
    // LinkedList of cards used as an order is important and is good for my
    // shuffling technique
    LinkedList<Card> cards = new LinkedList<>();
    
    /**
     * Constructs a Deck using the newDeck() method
     */
    Deck()
    {
        newDeck();
    }
    
    /**
     * newDeck()
     * adds 52 unique cards to the blank deck
     */
    private void newDeck()
    {
        Card.Suit[] suitList = Card.Suit.values();
        Card.Rank[] rankList = Card.Rank.values();
        
        int s = -1;
        int ss = suitList.length;
        
        int r;
        int rr = rankList.length;
        
        cards = new LinkedList<>();
        while(++s<ss)
        {
            r=-1;
            while(++r<rr)
            {
                cards.add(new Card(suitList[s],rankList[r]));
            }
        }
    }
    
    /**
     * deal()
     * removes and returns the first card
     * @return Card 
     */
    public Card deal()
    {
        if(cards.isEmpty())
        {
            return null;
        }
        else
        {
            return cards.removeFirst();
        }
    }
    
    /**
     * cardAtPos(int)
     * finds the card at position
     * @param int position
     * @return Card
     */
    private Card cardAtPos(int position)
    {
        return cards.get(position);
    }
    
    /**
     * shuffle()
     * implements the humanShuffler method with good randomisation values
     */
    public void shuffle()
    {
        handShuffle(100,7);
    }
    
    /**
     * size()
     * returns the number of cards in the Deck
     * @return int
     */
    public int size()
    {
        return cards.size();
    }

    /**
     * handShuffle
     * Simulates a human hand shuffle by reversing slices of the deck
     * 
     * @param times - number of times the process is performed
     * @param sliceSize - size of slice (will have a +/- 25% random sizing)
     */
    private void handShuffle(int times,int sliceSize)
    {
        
        // each slice will be baseSliceSize - Random()*randomSliceSize
        // resulting in a slice 75% - 125% of sliceSize
        int baseSliceSize = (int)(1.25 * sliceSize + 0.5);
        int randomSliceSize = (int)(0.5 * sliceSize + 0.5);
        Random rand = new Random();
        
        
        
        int t=-1;
        int s;
        LinkedList<Card> tempCards;
        LinkedList<Card> tempSlice;
        while(++t<times)
        {
            tempCards = new LinkedList<>();
            while(cards.size()>0)
            {
                tempSlice = new LinkedList<>();
                
                if(cards.size() <= baseSliceSize)
                {
                    while(cards.size()>0)
                    {
                        tempSlice.add(0, cards.removeFirst());
                    }
                    while(tempSlice.size()>0)
                    {
                        tempCards.add(0,tempSlice.removeFirst());
                    }
                }
                else
                {
                    s = baseSliceSize - rand.nextInt(randomSliceSize);
                    while(0<s--)
                    {
                        tempSlice.add(0, cards.removeFirst());
                    }
                    while(tempSlice.size()>0)
                    {
                        tempCards.add(0,tempSlice.removeFirst());
                    }
                }
            }
            cards = tempCards;
        }
    }
    @Override
    public Iterator<Card> iterator() {
        return new OddEvenIterator();
    }
    
    public class OddEvenIterator implements Iterator<Card>
    {
        int position = -1;
        @Override
        public Card next()
        {
            
            if(hasNext())
            {
                position++;
                if(position*2<size())
                {
                    return cardAtPos(position*2);
                }
                else
                {
                    return cardAtPos((position*2)-(2*(int)((size()-1)/2)+1));
                }
            }
            else
            {
                return null;
            }
        }
        
        @Override
        public boolean hasNext()
        {
            return position+1<size();
        }
    }
    
    
}
