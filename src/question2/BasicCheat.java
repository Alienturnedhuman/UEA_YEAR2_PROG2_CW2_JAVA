/*
 * Coursework 2 - Java Card Game
 */
package question2;

/**
 * Basic Cheat Game (adapted from supplied code)
 * @author Ben Dickson
 */

/*
Notes:
I have tried to implement the solution with as few changes to the source
as possible.

I spotted some errors I assume were deliberate: 

eg:
    printing out pointer for player in the console next to the number

    win loop not checking player 1

    player calling cheat always starting from player 1, which is unfair to that
        player

I did not make any changes to the interfaces, so did not add any new methods
to Bid,Player or Strategy. Instead, I just used the existing methods.

If I was doing this for myself, I would have changed the interfaces and
included a way to directly pass information (eg, how many cards other players
have, who called Cheat, etc) but there was no way to do this in a proper way.

The one notable change I madde was that every AI gets the opportunity to check
if it's a cheat - the original code stopped once one player had done so. 

This was so the AIs can keep a log of what is happening. The AI that calls first
is the one that wins/gets penalised.

I then added an additional cheat call with a blank hand, if cheat does get 
called, so the AIs know to reset their information, if they are one keeping
a track of the discard pile.


*/
import java.util.*;

public class BasicCheat implements CardGame{
    private Player[] players;
    private int nosPlayers;
    public static final int MINPLAYERS=5;
    private int currentPlayer;
    private Hand discards;
    private Bid currentBid;
    private int winningPlayer = -1;
    private int reportInterval=250;
    
    public boolean clog = false;    // made public, because having a get/set on
                                    // true/fallse is a little redundant...
    
    

    public BasicCheat(){
        this(MINPLAYERS);
    }
    /**
     * constructs a BasicCheat with Random AIs
     * @param n 
     */
    public BasicCheat(int n){
        nosPlayers=n;
        players=new Player[nosPlayers];
        for(int i=0;i<nosPlayers;i++)
                players[i]=(new BasicPlayer(
                        (new StrategyFactory()).getStrategy(
                                StrategyFactory.StratEnum.RANDOM),this));
        currentBid=new Bid();
        currentBid.setRank(Card.Rank.ACE);
        currentPlayer=0;
    }
    /**
     * Constructs a BasicCheat from an array of StrategyFactory enums
     * @param se 
     */
    public BasicCheat(StrategyFactory.StratEnum[] se)
    {
        nosPlayers=se.length;
        players=new Player[nosPlayers];
        for(int i=0;i<nosPlayers;i++)
                players[i]=(new BasicPlayer(
                        (new StrategyFactory(se[i])).getStrategy(),this));
        currentBid=new Bid();
        currentBid.setRank(Card.Rank.ACE);
        currentPlayer=0;
        
    }
    
    /**
     * sets the reporting interval in the game (and only if clog = true)
     * @param i 
     */
    public void setReportInterval(int i)
    {
        if(i<1)
        {
            i=1;
        }
        if(i>10000)
        {
            i=10000;
        }
        reportInterval=i;
    }
    
    /**
     * getWinningPlayer()
     * return index of winning player
     * @return int
     */
    public int getWinningPlayer()
    {
        return winningPlayer;
    }
    
    /**
     * cout()
     * console outputs if clog is true
     * @param s 
     */
    private void cout(String s)
    {
        if(clog)
        {
            System.out.println(s);
        }
    }

    @Override
    public boolean playTurn(){
//        lastBid=currentBid;
        //Ask player for a play,
        cout("Round: "+currentBid.getRank().getNext().name);
        currentBid=players[currentPlayer].playHand(currentBid);
        //
        cout("Player bid = "+currentBid);
         //Add hand played to discard pile
        discards.add(currentBid.getHand());
        //Offer all other players the chance to call cheat
        boolean cheat=false;
        int firstPlayerToCall=-1;
        // updated for loop to loop from player to right of player
        // as previous implementation unfairly punished first player
        for(int ti=1;ti<players.length;ti++){
            int i=(currentPlayer+ti)%players.length;
            // if condition should always be true however, I have left it
            // as a safety check...
            if(i!=currentPlayer){
                // ||cheat added so remains true if triggered
                cheat=players[i].callCheat(currentBid)||cheat;
                if(cheat&&firstPlayerToCall<0){
                    firstPlayerToCall=i;
                    cout("Player called cheat by Player "+(i+1));
                }
            }
        }
        if(cheat)
        {
            
            if(isCheat(currentBid)){	
//CHEAT CALLED CORRECTLY
//Give the discard pile of cards to currentPlayer who then has to play again                      
                players[currentPlayer].addHand(discards);
                cout("Player cheats!");
                cout("Adding cards to player "+(currentPlayer+1));

            }
            else{
//CHEAT CALLED INCORRECTLY
//Give cards to caller i who is new currentPlayer
                cout("Player Honest");
                currentPlayer=firstPlayerToCall;
                players[firstPlayerToCall].addHand(discards);
                cout("Adding cards to player "+(firstPlayerToCall+1));
            }
//If cheat is called, current bid reset to an empty bid with rank two whatever 
//the outcome
            currentBid=new Bid();
//Discards now reset to empty	
            discards=new Hand();
            
            // run the callCheat on the empty list. This allows cheat tracks
            // to update to the empty hand.
            // I would have otherwise liked to have included a new method for
            // sending play information to the strategy, however 
            // as the coursework does not allow for updates to the interface
            // as the interface classes are not included in the submission
            for(int i=0;i<players.length;i++){
                players[i].callCheat(currentBid);
            }
            
        }
        else
        {
//Go to the next player       
            cout("No Cheat Called");
        }
        // play can't stay on the failing player, as otherwise if not they
        // can get trapped in an infinite loop if they are known to have 
        // no twos.
        currentPlayer=(currentPlayer+1)%nosPlayers;
        return true;
    }
    @Override
    public int winner(){
            for(int i=0;i<nosPlayers;i++){
                    if(players[i].cardsLeft()==0)
                            return i;
            }
            return -1;

    }
    @Override
    public void initialise(){
            //Create Deck of cards
            Deck d=new Deck();
            d.shuffle();
            //Deal cards to players
            // changed to use Deck.deal()
            int count=0;
            while(d.size()>0)
            {
                players[count%nosPlayers].addCard(d.deal());
                count++;
            }
            //Initialise Discards
            discards=new Hand();
            //Chose first player
            currentPlayer=0;
            currentBid=new Bid();
            currentBid.setRank(Card.Rank.ACE);
    }
    public void playGame(){
            initialise();
            int c=0;
            Scanner in = new Scanner(System.in);
            boolean finished=false; 
            while(!finished){
                    //Play a hand
                    //System.out.println(" Cheat turn for player "+(currentPlayer+1));
                    playTurn();
                    //System.out.println(" Current discards =\n"+discards);
                    c++;
                    
                    // to play through the games more quickly, the game
                    // can report every reportInterval number of turns
                    if(c%reportInterval==0)
                    {
                        int ptc = 0;
                        int pi=-1;
                        int pii = players.length;
                        while(++pi<pii)
                        {
                            cout("Player "+pi+" hand size: "+players[pi].cardsLeft());
                            ptc+=players[pi].cardsLeft();
                        }
                        cout("\nCards in pile = "+discards.size());
                        ptc+=discards.size();
                        cout("\n Total Cards: "+ptc+"\n\n ");
                        cout(" Turn "+c+ " Complete. Press any key to continue or enter Q to quit>");
                        String str=in.nextLine();
                        if(str.equals("Q")||str.equals("q")||str.equals("quit"))
                                finished=true;

                    }
                    int w=winner();
                    // changed from w>0 to w>-1 as otherwise player 0 cannot win
                    if(w>-1){
                        winningPlayer = w;
                        clog=true;
                        cout("The Winner is Player "+
                                    (w+1)+" in "+c+" turns");
                            int ptc = 0;
                            int pi=-1;
                            int pii = players.length;
                            while(++pi<pii)
                            {
                                cout("Player "+(pi+1)+" hand size: "+players[pi].cardsLeft());
                                ptc+=players[pi].cardsLeft();
                            }
                            cout("\nCards in pile = "+discards.size());
                            finished=true;
                    }
            }
    }
    public static boolean isCheat(Bid b){
            for(Card c:b.getHand()){
                    if(c.getRank()!=b.r)
                            return true;
            }
            return false;
    }
    public static void main(String[] args){
            BasicCheat cheat=new BasicCheat();
            cheat.playGame();
    }
}
