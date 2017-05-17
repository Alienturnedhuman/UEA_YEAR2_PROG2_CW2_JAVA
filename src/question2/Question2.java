/*
 * Coursework 2 - Java Card Game
 */
package question2;

/**
 *  main method that runs 50 games and tallies the winner
 * @author Benjamin Dickson
 */
public class Question2 {

    public static void main(String[] args) {
        
        // create array of 5 for tallying winning player
        int i=-1;
        int[] winlog=new int[5];
        while(++i<5)
        {
            winlog[i]=0;
        }
        
        
        // run 50 games and increment winning player
        // Player 1 = BasicStrategy
        // Player 2 = BasicStrategy
        // Player 3 = MyStrategy
        // Player 4 = ThinkerStrategy
        // Player 5 = ThinkerStrategy
        
        
        StrategyFactory.StratEnum[] playerList = {
            StrategyFactory.StratEnum.MY
                ,
            StrategyFactory.StratEnum.BASIC
                ,
            StrategyFactory.StratEnum.THINKER
                ,
            StrategyFactory.StratEnum.THINKER
                ,
            StrategyFactory.StratEnum.MY
        };
        
        
        // run 50 games
        i=-1;
        while(++i<50)
        {
            System.out.println("Playing Game #"+(i+1));
            BasicCheat bc = new BasicCheat();
            bc.clog=false;  // disable console log (except for final results)
            bc.playGame();
            winlog[bc.getWinningPlayer()]++;
            
        }
        
        // display final results
        System.out.println("\n\nResults after 50 games:");
        i=-1;
        while(++i<5)
        {
            System.out.println("Player "+(i+1)+": "+winlog[i]);
        }
        
        // Example output below
        /*
run:
Playing Game #1
The Winner is Player 3 in 84 turns
Player 1 hand size: 7
Player 2 hand size: 15
Player 3 hand size: 0
Player 4 hand size: 10
Player 5 hand size: 18

Cards in pile = 2
Playing Game #2
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 28
Player 3 hand size: 0
Player 4 hand size: 1
Player 5 hand size: 1

Cards in pile = 21
Playing Game #3
The Winner is Player 5 in 50 turns
Player 1 hand size: 1
Player 2 hand size: 28
Player 3 hand size: 8
Player 4 hand size: 12
Player 5 hand size: 0

Cards in pile = 3
Playing Game #4
The Winner is Player 3 in 48 turns
Player 1 hand size: 28
Player 2 hand size: 1
Player 3 hand size: 0
Player 4 hand size: 15
Player 5 hand size: 1

Cards in pile = 7
Playing Game #5
The Winner is Player 4 in 49 turns
Player 1 hand size: 9
Player 2 hand size: 15
Player 3 hand size: 16
Player 4 hand size: 0
Player 5 hand size: 9

Cards in pile = 3
Playing Game #6
The Winner is Player 3 in 78 turns
Player 1 hand size: 11
Player 2 hand size: 12
Player 3 hand size: 0
Player 4 hand size: 1
Player 5 hand size: 20

Cards in pile = 8
Playing Game #7
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 8
Player 3 hand size: 0
Player 4 hand size: 13
Player 5 hand size: 1

Cards in pile = 29
Playing Game #8
The Winner is Player 5 in 50 turns
Player 1 hand size: 17
Player 2 hand size: 10
Player 3 hand size: 9
Player 4 hand size: 8
Player 5 hand size: 0

Cards in pile = 8
Playing Game #9
The Winner is Player 5 in 116 turns
Player 1 hand size: 14
Player 2 hand size: 12
Player 3 hand size: 14
Player 4 hand size: 11
Player 5 hand size: 0

Cards in pile = 1
Playing Game #10
The Winner is Player 3 in 49 turns
Player 1 hand size: 12
Player 2 hand size: 1
Player 3 hand size: 0
Player 4 hand size: 16
Player 5 hand size: 11

Cards in pile = 12
Playing Game #11
The Winner is Player 5 in 80 turns
Player 1 hand size: 13
Player 2 hand size: 22
Player 3 hand size: 8
Player 4 hand size: 6
Player 5 hand size: 0

Cards in pile = 3
Playing Game #12
The Winner is Player 3 in 48 turns
Player 1 hand size: 10
Player 2 hand size: 28
Player 3 hand size: 0
Player 4 hand size: 1
Player 5 hand size: 10

Cards in pile = 3
Playing Game #13
The Winner is Player 4 in 49 turns
Player 1 hand size: 19
Player 2 hand size: 15
Player 3 hand size: 7
Player 4 hand size: 0
Player 5 hand size: 8

Cards in pile = 3
Playing Game #14
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 26
Player 3 hand size: 0
Player 4 hand size: 13
Player 5 hand size: 11

Cards in pile = 1
Playing Game #15
The Winner is Player 4 in 49 turns
Player 1 hand size: 17
Player 2 hand size: 18
Player 3 hand size: 7
Player 4 hand size: 0
Player 5 hand size: 8

Cards in pile = 2
Playing Game #16
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 8
Player 3 hand size: 0
Player 4 hand size: 22
Player 5 hand size: 17

Cards in pile = 4
Playing Game #17
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 8
Player 3 hand size: 0
Player 4 hand size: 18
Player 5 hand size: 16

Cards in pile = 9
Playing Game #18
The Winner is Player 3 in 67 turns
Player 1 hand size: 26
Player 2 hand size: 23
Player 3 hand size: 0
Player 4 hand size: 1
Player 5 hand size: 1

Cards in pile = 1
Playing Game #19
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 1
Player 3 hand size: 0
Player 4 hand size: 1
Player 5 hand size: 1

Cards in pile = 48
Playing Game #20
The Winner is Player 1 in 93 turns
Player 1 hand size: 0
Player 2 hand size: 23
Player 3 hand size: 7
Player 4 hand size: 10
Player 5 hand size: 8

Cards in pile = 4
Playing Game #21
The Winner is Player 4 in 89 turns
Player 1 hand size: 11
Player 2 hand size: 17
Player 3 hand size: 11
Player 4 hand size: 0
Player 5 hand size: 12

Cards in pile = 1
Playing Game #22
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 1
Player 3 hand size: 0
Player 4 hand size: 1
Player 5 hand size: 1

Cards in pile = 48
Playing Game #23
The Winner is Player 4 in 84 turns
Player 1 hand size: 13
Player 2 hand size: 12
Player 3 hand size: 16
Player 4 hand size: 0
Player 5 hand size: 9

Cards in pile = 2
Playing Game #24
The Winner is Player 5 in 50 turns
Player 1 hand size: 8
Player 2 hand size: 14
Player 3 hand size: 7
Player 4 hand size: 21
Player 5 hand size: 0

Cards in pile = 2
Playing Game #25
The Winner is Player 4 in 49 turns
Player 1 hand size: 9
Player 2 hand size: 18
Player 3 hand size: 8
Player 4 hand size: 0
Player 5 hand size: 14

Cards in pile = 3
Playing Game #26
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 13
Player 3 hand size: 0
Player 4 hand size: 20
Player 5 hand size: 12

Cards in pile = 6
Playing Game #27
The Winner is Player 3 in 58 turns
Player 1 hand size: 16
Player 2 hand size: 16
Player 3 hand size: 0
Player 4 hand size: 15
Player 5 hand size: 1

Cards in pile = 4
Playing Game #28
The Winner is Player 2 in 52 turns
Player 1 hand size: 12
Player 2 hand size: 0
Player 3 hand size: 9
Player 4 hand size: 18
Player 5 hand size: 9

Cards in pile = 4
Playing Game #29
The Winner is Player 4 in 49 turns
Player 1 hand size: 12
Player 2 hand size: 15
Player 3 hand size: 12
Player 4 hand size: 0
Player 5 hand size: 1

Cards in pile = 12
Playing Game #30
The Winner is Player 3 in 48 turns
Player 1 hand size: 12
Player 2 hand size: 14
Player 3 hand size: 0
Player 4 hand size: 1
Player 5 hand size: 19

Cards in pile = 6
Playing Game #31
The Winner is Player 4 in 49 turns
Player 1 hand size: 12
Player 2 hand size: 12
Player 3 hand size: 11
Player 4 hand size: 0
Player 5 hand size: 16

Cards in pile = 1
Playing Game #32
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 1
Player 3 hand size: 0
Player 4 hand size: 24
Player 5 hand size: 22

Cards in pile = 4
Playing Game #33
The Winner is Player 4 in 49 turns
Player 1 hand size: 1
Player 2 hand size: 8
Player 3 hand size: 18
Player 4 hand size: 0
Player 5 hand size: 18

Cards in pile = 7
Playing Game #34
The Winner is Player 3 in 108 turns
Player 1 hand size: 5
Player 2 hand size: 15
Player 3 hand size: 0
Player 4 hand size: 7
Player 5 hand size: 19

Cards in pile = 6
Playing Game #35
The Winner is Player 3 in 48 turns
Player 1 hand size: 22
Player 2 hand size: 9
Player 3 hand size: 0
Player 4 hand size: 9
Player 5 hand size: 9

Cards in pile = 3
Playing Game #36
The Winner is Player 1 in 48 turns
Player 1 hand size: 0
Player 2 hand size: 21
Player 3 hand size: 9
Player 4 hand size: 12
Player 5 hand size: 6

Cards in pile = 4
Playing Game #37
The Winner is Player 3 in 48 turns
Player 1 hand size: 5
Player 2 hand size: 20
Player 3 hand size: 0
Player 4 hand size: 18
Player 5 hand size: 7

Cards in pile = 2
Playing Game #38
The Winner is Player 5 in 50 turns
Player 1 hand size: 10
Player 2 hand size: 10
Player 3 hand size: 15
Player 4 hand size: 16
Player 5 hand size: 0

Cards in pile = 1
Playing Game #39
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 13
Player 3 hand size: 0
Player 4 hand size: 10
Player 5 hand size: 24

Cards in pile = 4
Playing Game #40
The Winner is Player 3 in 48 turns
Player 1 hand size: 17
Player 2 hand size: 12
Player 3 hand size: 0
Player 4 hand size: 13
Player 5 hand size: 1

Cards in pile = 9
Playing Game #41
The Winner is Player 3 in 48 turns
Player 1 hand size: 17
Player 2 hand size: 14
Player 3 hand size: 0
Player 4 hand size: 14
Player 5 hand size: 1

Cards in pile = 6
Playing Game #42
The Winner is Player 3 in 46 turns
Player 1 hand size: 23
Player 2 hand size: 7
Player 3 hand size: 0
Player 4 hand size: 10
Player 5 hand size: 11

Cards in pile = 1
Playing Game #43
The Winner is Player 1 in 51 turns
Player 1 hand size: 0
Player 2 hand size: 8
Player 3 hand size: 13
Player 4 hand size: 7
Player 5 hand size: 17

Cards in pile = 7
Playing Game #44
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 9
Player 3 hand size: 0
Player 4 hand size: 30
Player 5 hand size: 6

Cards in pile = 6
Playing Game #45
The Winner is Player 3 in 48 turns
Player 1 hand size: 1
Player 2 hand size: 1
Player 3 hand size: 0
Player 4 hand size: 1
Player 5 hand size: 1

Cards in pile = 48
Playing Game #46
The Winner is Player 5 in 50 turns
Player 1 hand size: 9
Player 2 hand size: 17
Player 3 hand size: 17
Player 4 hand size: 7
Player 5 hand size: 0

Cards in pile = 2
Playing Game #47
The Winner is Player 3 in 48 turns
Player 1 hand size: 7
Player 2 hand size: 7
Player 3 hand size: 0
Player 4 hand size: 17
Player 5 hand size: 18

Cards in pile = 3
Playing Game #48
The Winner is Player 3 in 48 turns
Player 1 hand size: 8
Player 2 hand size: 15
Player 3 hand size: 0
Player 4 hand size: 8
Player 5 hand size: 19

Cards in pile = 2
Playing Game #49
The Winner is Player 1 in 54 turns
Player 1 hand size: 0
Player 2 hand size: 21
Player 3 hand size: 14
Player 4 hand size: 8
Player 5 hand size: 6

Cards in pile = 3
Playing Game #50
The Winner is Player 5 in 65 turns
Player 1 hand size: 1
Player 2 hand size: 12
Player 3 hand size: 11
Player 4 hand size: 21
Player 5 hand size: 0

Cards in pile = 7


Results after 50 games:
Player 1: 4
Player 2: 1
Player 3: 28
Player 4: 9
Player 5: 8
BUILD SUCCESSFUL (total time: 0 seconds)
        
        */
    }
}
