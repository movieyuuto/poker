package card;

public class Poker {

	private CardDeck cardDeck;

	private Cards cards1,cards2;


	public Poker()
	{
		int i;
		CardDeck deck = new CardDeck();

		cards1 = new Cards(5, "ツーペア");
		cards1.addCard(1);
		cards1.addCard(14);
		cards1.addCard(28);
		cards1.addCard(41);
		cards1.addCard(52);

		cards2 = new Cards(5, "なし");

		deck.shuffle();

		for(i=0;i<5;i++)
		{
			cards2.addCard(1);
		}


	}

	public void printCards()
	{
		cards1.printSuitsNumbers();
		cards2.printSuitsNumbers();
	}

	public static void main(String args[])
	{
		Poker poker = new Poker();
		poker.printCards();
	}

}
