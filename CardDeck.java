package card;

import java.util.ArrayList;
import java.util.Collections;


public class CardDeck
{
	ArrayList<Integer> cards;

    public CardDeck()
    {
	   cards = new ArrayList<>();
	   for(int i=1;i<=52;i++)
	   {
		   cards.add(i);
	   }

    }

    public void printCards()
    {
	   for(int i=0;i<cards.size();i++)
	   {
		   int c = cards.get(i);
		   String s=null;
		   s = getSuitsNumber(i);
		   System.out.print(s+" ");
	   }
	   System.out.println();
    }

    public int getSize()
    {
    	return cards.size();
    }

    private String getSuitsNumber(int index)
    {
    	int c = cards.get(index);
    	String u=null;
    	String s=null;

    	if(c%13 == 1)
    	{
    		s="A";
    	}
    	else if(c%13 == 11)
    	{
    		s="J";
    	}
    	else if(c%13 == 12)
    	{
    		s="Q";
    	}
    	else if(c%13 == 0)
    	{
    		s="K";
    	}
    	else
    	{
    		if(c%13 == 10)
    		{
    			s=""+0;
    		}
    		else
    		{
    			s=""+c%13;
    		}

    	}

    	if(c/13 == 0)
    	{
    		u = "S";
    	}
    	else if(c/13 == 1)
    	{
    		u = "H";
    	}
    	else if(c/13 ==2)
    	{
    		u = "D";
    	}
    	else
    	{
    		u="C";
    	}

    	return  u+s;
    }

    public void shuffle()
    {
    	Collections.shuffle(cards);
    }

    public int getNextCard()
    {
    	return cards.remove(0);
    }

    public static void main(String args[])
    {
    	CardDeck deck = new CardDeck();
    	deck.printCards();
    	deck.getSize();
    	System.out.println("------カードとりだし-------");
    	deck.getNextCard();
    	deck.getSize();

    	deck.printCards();


    }

   }