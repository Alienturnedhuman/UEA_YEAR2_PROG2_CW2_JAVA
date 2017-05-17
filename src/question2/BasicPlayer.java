/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;

/**
 *
 * @author Ben Dickson
 */
public class BasicPlayer implements Player
{
    private Strategy strategy;
    private CardGame cardGame;
    private Hand hand;
    
    
    BasicPlayer(Strategy cStrategy,CardGame cCardGame)
    {
        this.strategy = cStrategy;
        this.cardGame = cCardGame;
        this.hand = new Hand();
    }

    @Override
    public void addCard(Card c) {
        this.hand.add(c);
    }

    @Override
    public void addHand(Hand h) {
        this.hand.add(h);
    }

    @Override
    public int cardsLeft() {
        return this.hand.size();
    }

    @Override
    public void setGame(CardGame g) {
        this.cardGame = g;
    }

    @Override
    public void setStrategy(Strategy s) {
        this.strategy = s;
    }

    @Override
    public Bid playHand(Bid b) {
        return strategy.chooseBid(b, hand, strategy.cheat(b, hand));
    }

    @Override
    public boolean callCheat(Bid b) {
        return strategy.callCheat(hand, b);
    }
}