/*
 * Coursework 2 - Java Card Game
 */
package question1;

import java.util.Comparator;

/**
 * 
 * @author Ben Dickson
 */
public class Card implements Comparable<Card>
{
    private Rank rank;
    private Suit suit;
    
    public enum Suit
    {
        CLUBS("Clubs")
        ,
        DIAMONDS("Diamonds")
        ,
        HEARTS("Hearts")
        ,
        SPADES("Spades")
        ;   
                
        String name;
        char letter;
        
        Suit(String tName)
        {
            this.name = tName;
            this.letter = tName.charAt(0);
        }
        
        
    }
    
    public enum Rank
    {
        TWO("Two",2)
        ,
        THREE("Three",3)
        ,
        FOUR("Four",4)
        ,
        FIVE("Five",5)
        ,
        SIX("Six",6)
        ,
        SEVEN("Seven",7)
        ,
        EIGHT("Eight",8)
        ,
        NINE("Nine",9)
        ,
        TEN("Ten",10)
        ,
        JACK("Jack","J",10,10)
        ,
        QUEEN("Queen","Q",10,11)
        ,
        KING("King","K",10,12)
        ,
        
        ACE("Ace","A",11,13)
        ;
        int value;
        String name;
        String shortName;
        int rank;
        Rank(String tName, String tShortName,int tValue,int tRank)
        {
            this.name = tName;
            this.shortName = tShortName;
            this.value = tValue;
            this.rank = tRank;
        }
        Rank(String tName, int tValue)
        {
            this.name = tName;
            this.shortName = Integer.toString(tValue);
            this.value = tValue;
            this.rank = tValue-1;
        }    
        private static Rank[] rankList = values(); 
        
        public Rank getNext()
        {
            int r=this.ordinal()+1;
            if(
                r > -1
                &&
                r < rankList.length
            )
            {
                return rankList[r];
            }
            else
            {
                return rankList[0];
            }
        }
        
    }
    
    Card(Suit tSuit,Rank tRank)
    {
        this.suit = tSuit;
        this.rank = tRank;
    }
    
    /**
     * getRank()
     * returns the rank of the card
     * @return Card.Rank
     */
    Rank getRank()
    {
        return this.rank;
    }
    
    
    /**
     * getSuit()
     * returns the suit of the card
     * @return Card.Suit
     */
    Suit getSuit()
    {
        return this.suit;
    }
    
    /**
     * difference(Card,Card)
     * returns the difference in Rank between A and B
     * @param Card A
     * @param Card B
     * @return int
     */
    public static int difference(Card A,Card B)
    {
        return A.getRank().rank - B.getRank().rank;
    }
    
   
    /**
     * differenceValue(Card,Card)
     * returns the difference in Value between A and B
     * @param Card A
     * @param Card B
     * @return int
     */
    public static int differenceValue(Card A,Card B)
    {
        return A.getRank().value - B.getRank().value;
    }
    
    /**
     * Comparator for comparing cards in reverse rank order (then suit)
     */
    public static class CompareDescending implements Comparator<Card>
    {
        @Override
        public int compare(Card a, Card b)
        {
            
            int r = Card.difference(b, a);
            if(r==0)
            {
                return a.suit.ordinal()-b.suit.ordinal();
            }
            else
            {
                return r;
            }
        }
    }
    /**
     * Comparator for comparing cards in Suit (then rank)
     */
    public static class CompareSuit implements Comparator<Card>
    {
        @Override
        public int compare(Card a, Card b)
        {
            int r = a.suit.ordinal()-b.suit.ordinal();
            if(r==0)
            {
                return a.rank.ordinal()-b.rank.ordinal();
            }
            else
            {
                return r;
            }
        }
    }
    
    /**
     *
     * @param cCard
     * @return
     */
    @Override
    public int compareTo(Card cCard)
    {
        return Card.difference(this,cCard);
    }
    
    @Override
    public String toString()
    {
        return this.rank.name+" of "+this.suit.name;
    }
    public String toString(boolean shortHand)
    {
        if(shortHand)
        {
            return this.rank.shortName+this.suit.letter;
        }
        else
        {
             return this.rank.name+" of "+this.suit.name;
        }
    }
}