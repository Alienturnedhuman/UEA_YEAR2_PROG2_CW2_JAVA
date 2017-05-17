/*
 * Coursework 2 - Java Card Game
 */
package question2;

import java.util.Random;

/**
 * StrategyFactory implementation
 * I included additional functionality on top of those methods
 * asked for in the coursework
 * @author Ben Dickson
 */
public class StrategyFactory {
    StratEnum defaultStrategy;
    Random random = new Random();
  
    public enum StratEnum
    {
        BASIC(new BasicStrategy())
        ,
        THINKER(new BasicStrategy())
        ,
        MY(new BasicStrategy())
        ,
        HUMAN(new HumanStrategy())
        ,
        RANDOM(true)
        ;
        boolean isRandom=false;
        Strategy strategy;
        
        StratEnum()
        {
            this.strategy=new BasicStrategy();
        }
        StratEnum(Strategy s)
        {
            this.strategy=s;
        }
        StratEnum(boolean r)
        {
            this.isRandom = r;
        }
        
    }
    public enum AIEnum
    {
        BASIC(new BasicStrategy())
        ,
        THINKER(new ThinkerStrategy())
        ,
        MY(new MyStrategy())
        ;
        
        Strategy strategy;
        
        AIEnum()
        {
            this.strategy=new BasicStrategy();
        }
        AIEnum(Strategy s)
        {
            this.strategy=s;
        }
        
    }  
    
    /**
     * StrategyFactory()
     * Default Constructor sets StrategyFactory default to BASIC
     */
    StrategyFactory()
    {
        defaultStrategy = StratEnum.BASIC;
    }
    
    /**
     * StrategyFactory(String sName)
     * Sets default strategy in Factory to value in String
     * @param sName 
     *      gets a strategy from a string name (case insensitive)
     *      Thinker = ThinkerStrategy
     *      Human = HumanStrategy
     *      My = MyStrategy
     *      Random = random AI strategy (not HumanStrategy)
     *      anything else = BasicStrategy
     */
    StrategyFactory(String sName)
    {
        sName = sName.toUpperCase();
        if(sName.equals("THINKER"))
        {
            defaultStrategy = StratEnum.THINKER;
        }
        else if(sName.equals("HUMAN"))
        {
            defaultStrategy = StratEnum.HUMAN;
        }
        else if(sName.equals("MY"))
        {
            defaultStrategy = StratEnum.MY;
        }
        else if(sName.equals("RANDOM"))
        {
            defaultStrategy = StratEnum.RANDOM;
        }
        else
        {
            defaultStrategy = StratEnum.BASIC;
        }
    }
    StrategyFactory(StratEnum s)
    {
        defaultStrategy = s;
    }
    
    
    
    
    /**
     * getStrategy()
     * @return default Strategy
     */
    public Strategy getStrategy()
    {
        return getStrategy(defaultStrategy);
    }
    
    /**
     * getStrategy(String)
     * @param String sName
     *      gets a strategy from a string name (case insensitive)
     *      Thinker = ThinkerStrategy
     *      Human = HumanStrategy
     *      My = MyStrategy
     *      Random = random AI strategy (not HumanStrategy)
     *      Basic = BasicStrategy
     *      Default = BasicStrategy
     *      anything else = default
     * @return Strategy
     */
    public Strategy getStrategy(String sName)
    {
        sName = sName.toUpperCase();
        if(sName.equals("THINKER"))
        {
            return new ThinkerStrategy();
        }
        else if(sName.equals("HUMAN"))
        {
            return new HumanStrategy();
        }
        else if(sName.equals("MY"))
        {
            return new MyStrategy();
        }
        else if(sName.equals("RANDOM"))
        {
            return randomAI();
        }
        else if(sName.equals("BASIC"))
        {
            return new BasicStrategy();
        }
        else if(sName.equals("DEFAULT"))
        {
            return getStrategy(defaultStrategy);
        }
        else
        {
            return getStrategy(defaultStrategy);
        }
    }
    
    /**
     * getStrategy
     * gets a strategy from a StratEnum
     * THINKER = ThinkerStrategy
     * HUMAN = HumanStrategy
     * MY = MyStrategy
     * RANDOM = random AI strategy (not HumanStrategy)
     * BASIC = BasicStrategy
     * DEFAULT = default Strategy
     * @param sName
     * @return Strategy
     */
    public Strategy getStrategy(StratEnum sName)
    {
        if(sName.isRandom)
        {
            return randomAI();
        }
        else
        {
            return sName.strategy;
        }
    }
    
    
    
    /**
     * randomAi()
     * returns a random AI strategy (not HumanStrategy)
     * @return Strategy
     */
    private Strategy randomAI()
    {
        AIEnum[] a = AIEnum.values();
        int ac = a.length;
        return a[random.nextInt(ac)].strategy;
        
    }
    
}
