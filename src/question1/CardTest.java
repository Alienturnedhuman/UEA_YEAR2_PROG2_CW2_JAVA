/*
 * Coursework 2 - Java Card Game
 */
package question1;

import java.util.Iterator;

/**
 * Tests the Card/Hand/Deck classes
 * @author Benjamin Dickson
 */
public class CardTest {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    
        // generates Deck and array of hands
        Deck allCards = new Deck();
        Hand[] tHand = new Hand[4];
        int i=-1;
        while(++i<4)
        {
            tHand[i] = new Hand();
        }
        
        
        // deals 5 cards to hand 1 (these will be in order)
        i=-1;
        while(++i<5)
        {
            Card c = allCards.deal();
            tHand[0].add(c);
        }
        
        // testing compare suit
        allCards.cards.sort(new Card.CompareSuit());
        
        System.out.println("\n\nCompare suit:");
        for (Iterator<Card> it = allCards.cards.iterator(); it.hasNext();) {
            Card c = it.next();
            System.out.println(c.toString(false));
        }
        
        
        //testing shuffle
        allCards.shuffle();
        
        System.out.println("\n\nShuffled:");
        for (Iterator<Card> it = allCards.cards.iterator(); it.hasNext();) {
            Card c = it.next();
            System.out.println(c.toString(false));
        }
        
        // add 5 cards to other thre hands (these will be random)
        int j=0;
        while(++j<4)
        {
            
            i=-1;
            while(++i<5)
            {
                tHand[j].add(allCards.deal());
            }
        }
        
        // output results
        j=-1;
        while(++j<4)
        {
            System.out.println("\n\nHand #"+Integer.toString(j));
            
            System.out.println(tHand[j].toString());
            System.out.println("Is Flush: "+(tHand[j].isFlush()?"YES":"NO"));
            System.out.println("Is Straight: "+(tHand[j].isStraight()?"YES":"NO"));
            System.out.println("Value: "+(Integer.toString(tHand[j].handValue())));
            System.out.println("Clubs: "+(Integer.toString(tHand[j].countSuit(Card.Suit.CLUBS))));
            System.out.println("Kings: "+(Integer.toString(tHand[j].countRank(Card.Rank.KING))));
        }
        
        
        Card[] testStraightAce = new Card[5];
        testStraightAce[0] = new Card(Card.Suit.CLUBS,Card.Rank.ACE);
        testStraightAce[1] = new Card(Card.Suit.CLUBS,Card.Rank.TWO);
        testStraightAce[2] = new Card(Card.Suit.CLUBS,Card.Rank.THREE);
        testStraightAce[3] = new Card(Card.Suit.CLUBS,Card.Rank.FOUR);
        testStraightAce[4] = new Card(Card.Suit.CLUBS,Card.Rank.FIVE);
        
        Hand testStraightHand = new Hand(testStraightAce);
        System.out.println("\n\nTest StraightFixed on A to 5");
        System.out.println("Is Straight: "+(testStraightHand.isStraight()?"YES":"NO"));
        System.out.println("Is StraightFixed: "+(testStraightHand.isStraightFixed()?"YES":"NO"));
        
        testStraightAce[4] = new Card(Card.Suit.CLUBS,Card.Rank.SIX);
        
        testStraightHand = new Hand(testStraightAce);
        System.out.println("\n\nTest StraightFixed on A,2,3,4,6");
        System.out.println("Is Straight: "+(testStraightHand.isStraight()?"YES":"NO"));
        System.out.println("Is StraightFixed: "+(testStraightHand.isStraightFixed()?"YES":"NO"));
        
        
        testStraightAce[0] = new Card(Card.Suit.CLUBS,Card.Rank.ACE);
        testStraightAce[1] = new Card(Card.Suit.CLUBS,Card.Rank.KING);
        testStraightAce[2] = new Card(Card.Suit.CLUBS,Card.Rank.QUEEN);
        testStraightAce[3] = new Card(Card.Suit.CLUBS,Card.Rank.JACK);
        testStraightAce[4] = new Card(Card.Suit.CLUBS,Card.Rank.TEN);
        
        testStraightHand = new Hand(testStraightAce);
        System.out.println("\n\nTest StraightFixed on 10 to A");
        System.out.println("Is Straight: "+(testStraightHand.isStraight()?"YES":"NO"));
        System.out.println("Is StraightFixed: "+(testStraightHand.isStraightFixed()?"YES":"NO"));
        
        // EXAMPLE OUTPUT BELOW
        
        /*
Compare suit:
Seven of Clubs
Eight of Clubs
Nine of Clubs
Ten of Clubs
Jack of Clubs
Queen of Clubs
King of Clubs
Ace of Clubs
Two of Diamonds
Three of Diamonds
Four of Diamonds
Five of Diamonds
Six of Diamonds
Seven of Diamonds
Eight of Diamonds
Nine of Diamonds
Ten of Diamonds
Jack of Diamonds
Queen of Diamonds
King of Diamonds
Ace of Diamonds
Two of Hearts
Three of Hearts
Four of Hearts
Five of Hearts
Six of Hearts
Seven of Hearts
Eight of Hearts
Nine of Hearts
Ten of Hearts
Jack of Hearts
Queen of Hearts
King of Hearts
Ace of Hearts
Two of Spades
Three of Spades
Four of Spades
Five of Spades
Six of Spades
Seven of Spades
Eight of Spades
Nine of Spades
Ten of Spades
Jack of Spades
Queen of Spades
King of Spades
Ace of Spades


Shuffled:
Queen of Diamonds
Four of Diamonds
Jack of Clubs
Five of Hearts
Ten of Diamonds
King of Clubs
Queen of Spades
Nine of Hearts
Eight of Diamonds
Seven of Spades
Six of Diamonds
Jack of Hearts
Ace of Spades
Four of Spades
Five of Spades
Ace of Diamonds
Jack of Diamonds
Eight of Spades
Nine of Clubs
King of Spades
King of Diamonds
Queen of Clubs
Six of Spades
Three of Diamonds
Two of Spades
Ten of Clubs
Eight of Hearts
Five of Diamonds
Ace of Hearts
Six of Hearts
King of Hearts
Seven of Diamonds
Ace of Clubs
Eight of Clubs
Ten of Spades
Four of Hearts
Two of Hearts
Jack of Spades
Seven of Clubs
Two of Diamonds
Queen of Hearts
Nine of Spades
Ten of Hearts
Three of Spades
Three of Hearts
Seven of Hearts
Nine of Diamonds


Hand #0
Two of Clubs
Three of Clubs
Four of Clubs
Five of Clubs
Six of Clubs
Is Flush: YES
Is Straight: YES
Value: 20
Clubs: 5
Kings: 0


Hand #1
Queen of Diamonds
Four of Diamonds
Jack of Clubs
Five of Hearts
Ten of Diamonds
Is Flush: NO
Is Straight: NO
Value: 39
Clubs: 1
Kings: 0


Hand #2
King of Clubs
Queen of Spades
Nine of Hearts
Eight of Diamonds
Seven of Spades
Is Flush: NO
Is Straight: NO
Value: 44
Clubs: 1
Kings: 1


Hand #3
Six of Diamonds
Jack of Hearts
Ace of Spades
Four of Spades
Five of Spades
Is Flush: NO
Is Straight: NO
Value: 36
Clubs: 0
Kings: 0
        
        
Test StraightFixed on A to 5
Is Straight: NO
Is StraightFixed: YES


Test StraightFixed on A,2,3,4,6
Is Straight: NO
Is StraightFixed: NO


Test StraightFixed on 10 to A
Is Straight: YES
Is StraightFixed: YES
BUILD SUCCESSFUL (total time: 0 seconds)
      
        */
        
    }
    
}
