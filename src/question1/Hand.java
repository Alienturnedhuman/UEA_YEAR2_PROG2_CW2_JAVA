/*
 * Coursework 2 - Java Card Game
 */
package question1;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Ben Dickson
 */
public class Hand implements Iterable<Card> {
    
    // cards is the cards in any order sorted to
    private LinkedList<Card> cards = new LinkedList<>();
    
    // orderAdded is a list of the cards in the order added
    private LinkedList<Card> orderAdded = new LinkedList<>();
    
    // these are stored just to stop repeated method calls 
    final private int rankLength = Card.Rank.values().length;
    final private int suitLength = Card.Suit.values().length;
    
    // stores information on the hand (maintained by updateCardArrays())
    private int[] rankCount = new int[rankLength];
    private int[] suitCount = new int[suitLength];
    private int valueOfHand;
    
    // used for drawing a random card
    Random random = new Random();
    
    // default constructor just tallies up the empty array
    Hand()
    {
        updateCardArrays();
    }
    
    // passes an array of cards to the hand and tallies up
    Hand(Card[] c)
    {
        int i=-1;
        int ii=c.length;
        while(++i<ii)
        {
            insertCard(c[i]);
        }
        updateCardArrays();
    }
    
    /**
     * updateCardArrays()
     * method for tallying up the information on the hand
     */
    private void updateCardArrays()
    {
        int i=-1;
        while(++i<suitLength)
        {
            suitCount[i]=0;
        }
        i=-1;
        while(++i<rankLength)
        {
            rankCount[i]=0;
        }
        valueOfHand = 0;
        
        int suitIndex;
        int rankIndex;
        
        for(Iterator<Card> it = cards.iterator(); it.hasNext();)
        {
            Card c = it.next();
            suitIndex = c.getSuit().ordinal();
            rankIndex = c.getRank().ordinal();
            
            suitCount[suitIndex]++;
            rankCount[rankIndex]++;
            
            valueOfHand += Card.Rank.values()[rankIndex].value;
        }
        
        LinkedList<Card> tOrderAdded = new LinkedList<>();
        for(Iterator<Card> it = orderAdded.iterator(); it.hasNext();)
        {
            Card c=it.next();
            if(cards.contains(c))
            {
                tOrderAdded.add(c);
            }
        }
        orderAdded = tOrderAdded;
    }
    /**
     * returns the size of the Hand
     * @return int
     */
    public int size()
    {
        return cards.size();
    }
    
    /**
     * getHandAsSet()
     * @return returns a LinkedList of the set
     */
    public LinkedList<Card> getHandAsSet()
    {
        return cards;
    }
    
    /**
     * getHandAsArray()
     * returns array of the cards in the Hand
     * @return Card[]
     */
    public Card[] getHandAsArray()
    {
        Card[] r = new Card[cards.size()];
        int i=-1;
        for(Iterator<Card> it = cards.iterator(); it.hasNext();)
        {
            r[++i]= it.next();
        }
        return r;
    }
    
    /**
     * insertCard()
     * Inserts card into Hand if it is not already in it
     * (into both lists)
     * @param c 
     */
    private void insertCard(Card c)
    {
        if(!cards.contains(c))
        {
            cards.add(c);
            orderAdded.add(c);
        }
    }
    
    /**
     * contains(Card)
     * Does the Hand contain a card?
     * @param Card c - card being checked for
     * @return boolean
     */
    public boolean contains(Card c)
    {
        return cards.contains(c);
    }
    
    /**
     * add(Card)
     * Add a single card to the hand and update Arrays
     * @param Card c - card being added
     */
    public void add(Card c)
    {
        insertCard(c);
        updateCardArrays();
    }
    
    /**
     * add(Card[])
     * Adds an array of cards to the hand and updates the Array
     * @param Card[] c - cards being added
     */
    public void add(Card[] c)
    {
        int i=-1;
        int ii = c.length;
        while(++i<ii)
        {
            insertCard(c[i]);
        }
        updateCardArrays();
    }
    
    /**
     * add(Collection<Card>)
     * Adds a collection of Cards to the hand and updates the array
     * @param Collection<Card> c 
     */
    public void add(Collection<Card> c)
    {
        for(Iterator<Card> it = c.iterator(); it.hasNext();)
        {
            Card card=it.next();
            insertCard(card);
        }
        updateCardArrays();
    }
    
    /**
     * add(Hand)
     * Adds a hand to the hand and updates the array
     * @param Hand h - the hand being added 
     */
    public void add(Hand h)
    {
        Card[] c = h.getHandAsArray();
        
        int i=-1;
        int ii = c.length;
        while(++i<ii)
        {
            insertCard(c[i]);
        }
        updateCardArrays();
    }
    
    /**
     * remove(Card)
     * Removes the card from the hand if it is in it
     * @param Card c 
     */
    public void remove(Card c)
    {
        if(cards.contains(c))
        {
            cards.remove(c);
            updateCardArrays();
        }
    }
    
    /**
     * remove(Hand)
     * Removes any matching cards from Hand h from the Hand
     * @param Hand h - the hand being compared to 
     */
    public void remove(Hand h)
    {
        LinkedList<Card> c = h.getHandAsSet();
        cards.removeAll(c);
        updateCardArrays();
    }
    
    /**
     * remove(int)
     * removes a card from position p
     * @param int p 
     */
    public void remove(int p)
    {
        if(p>-1&&p<cards.size())
        {
            int i=-1;
            boolean searching = true;
            for(Iterator<Card> it = cards.iterator(); searching&&it.hasNext();)
            {
                Card c = it.next();
                if(++i==p)
                {
                    searching=false;
                    remove(c);
                }
            }
            updateCardArrays();
            
        }
    }
    
    /**
     * takeRandomCard()
     * removes a random Card from the Hand and returns it
     * @return Card
     */
    public Card takeRandomCard()
    {
        int p = random.nextInt(size());
        Card c = cards.get(p);
        remove(c); 
        
        return c;
    }
    
    /**
     * takeCardFromPos(int)
     * removes a card from the position in the hand and returns it
     * will wrap around the deck, and negative values count back from the end
     * @param int pos - position in the list
     * @return Card
     */
    public Card takeCardFromPos(int pos)
    {
        
        int tSize = this.size();
        if(pos>=tSize)
        {
            pos%=tSize;
        }
        if(pos<0)
        {
            pos=(((pos%tSize)+tSize+tSize)%tSize);
        }
        
        
        Card c = cards.get(pos);
        remove(c); 
        
        return c;
    }
    
    /**
     * sortAscending()
     * sorts the cards into ascending order
     */
    public void sortAscending()
    {
        Collections.sort(cards);
    }
    
    /**
     * sortDescending()
     * sorts the cards into descending order
     */
    public void sortDescending()
    {
        cards.sort(new Card.CompareDescending());
    }
    
    /**
     * handValue()
     * returns the value of the hand
     * @return  int
     */
    public int handValue()
    {
        return valueOfHand;
    }
    
    /**
     * countSuit(Card.Suit)
     * returns the number of this suit in the hand
     * @param cs
     * @return int
     */
    public int countSuit(Card.Suit cs)
    {
        return suitCount[cs.ordinal()];
    }
    
    /**
     * countRank(Card.Rank)
     * returns the number of this rank in the hand
     * @param cr
     * @return int
     */
    public int countRank(Card.Rank cr)
    {
        return rankCount[cr.ordinal()];
    }
    
    /**
     * isFlush();
     * is the hand a flush? (only of one suit)
     * @return boolean
     */
    public boolean isFlush()
    {
        int r = 0;
        int i = -1;
        while(++i<suitLength)
        {
            r += suitCount[i]>0?1:0;
        }
        return r==1;
    }
    
    /**
     * isStraight()
     * Is the hand a straight (values in ascending order)
     * Will return true for all straights including A 2 3 4 5 and 10 J Q K A
     * But not straights through Ace (eg Q K A 2 3)
     * @return boolean
     */
    public boolean isStraight()
    {
        Hand tempHand = new Hand(getHandAsArray());
        tempHand.sortAscending();
        
        Iterator<Card> it = tempHand.cards.iterator();
        Card c=it.next();
        
        int lastRank = c.getRank().ordinal();
        
        // needed for ACE through FIVE flush
        /* 
            note - amended from: 
                if(lastRank<rankLength-1)
            in original submission
        */
        if(!(lastRank<rankLength-1))
        {
            lastRank=-1;
        }
        boolean stillStraight=true;
        
        for(;stillStraight&&it.hasNext();)
        {
            c=it.next();
            int nextRank = c.getRank().ordinal();
            stillStraight = (nextRank-lastRank==1);
            lastRank=nextRank;
        }
        
        return stillStraight;
    }
    
    
    /*
        Note, I saw Tony's email after going to bed, after which I had realised
        While my fix I had mentioned to him in the email did fix it for most
        cases, it didn't implement the A2345 solution I'd intended to 
        This is due to me originally coding Ace to have Rank 1 and then
        forgetting it wouldn't sort to the beginning, and generally not thinking
        things through.
    
        Anyway, this presented me with a dilemma, as I did not think it was
        fair to amend this after the deadline, but at the same time, didn't
        want to look like an idiot. So below I have included isStraightFixed()
        to show how I would have implemented it, but the mark on my coursework
        should be based on what is submitted above
    */
    public boolean isStraightFixed()
    {
        Hand tempHand = new Hand(getHandAsArray());
        tempHand.sortAscending();
        
        Iterator<Card> it = tempHand.cards.iterator();
        Card c=it.next();
        
        int lastRank = c.getRank().ordinal();
        boolean stillStraight=true;
        
        int firstRank = lastRank;
        
        for(;stillStraight&&it.hasNext();)
        {
            c=it.next();
            int nextRank = c.getRank().ordinal();
            stillStraight = (nextRank-lastRank==1);
            
            // A2345 will actually be 2345A when sorted As
            // so if there is no next but the straight broke on this turn
            // it is a straight if the 1st card was 2, and the last card was Ace
            // so checking for rank 1 and rank 13 can preserve the straight
            if(!it.hasNext()&&!stillStraight)
            {
                stillStraight = (firstRank==0&&nextRank==12);
            }
                
            lastRank=nextRank;
        }
        
        return stillStraight;
    }
    
    @Override
    public String toString()
    {
        StringBuilder r = new StringBuilder();
        if(cards.size()<1)
        {
            return "Empty Hand";
        }
        Iterator<Card> it = cards.iterator();
        Card c = it.next();     
        r.append(c.toString());
        for(; it.hasNext();)
        {
            c = it.next();
            r.append("\n"+c.toString());
        }
        
        return r.toString();
    }
    
    /**
     * toString(boolean)
     * If shortForm==true, then return shortForm of card name
     * eg: 5H = Five of Hearts
     * @param boolean shortForm
     * @return 
     */
    public String toString(boolean shortForm)
    {
        StringBuilder r = new StringBuilder();
        if(cards.size()<1)
        {
            return "Empty Hand";
        }
        Iterator<Card> it = cards.iterator();
        Card c = it.next();     
        r.append(c.toString(shortForm));
        for(; it.hasNext();)
        {
            c = it.next();
            if(shortForm)
            {
                r.append("\t"+c.toString(true));
            }
            else
            {
                r.append("\n"+c.toString());
            }
            
        }
        
        return r.toString();
    }


    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

}
