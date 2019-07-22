package card;

public class Cards {
	private int cards[];

	private int num;

	private String name;

	public Cards(int n,String name)
	{
		cards = new int[n];
		num = 0;
		this.name=name;
	}

	public void addCard(int card)
	{
		cards[num] = card;
		Sort();
		num++;
	}

	public int getCard(int index)
	{
		return cards[index];
	}

	public void printCards()
	{
		System.out.print("printCards():");
		for(int i=0;i<5;i++)
		{
			System.out.print(cards[i]+" ");
		}
		System.out.println();
	}

	public void printNumbers()
	{
		System.out.print("printNumbers():");

	 for(int i=0;i<5;i++)
	 {
		if(cards[i]%13 == 1)
		{
			System.out.print("A");
		}

		else if(cards[i]%13 == 11)
		{
			System.out.print("J ");
		}
		else if(cards[i]%13 == 12)
		{
			System.out.print("Q ");
		}
		else if(cards[i]%13 == 0)
		{
			System.out.print("K ");
		}
		else
		{
			if(cards[i]%13 == 10)
			{
		     System.out.print("0"+" ");
			}
			else
			{
			 System.out.print(" "+cards[i]%13+" ");
			}
		}
	 }
	 System.out.println();
	}

	public String getNumber(int index)
	{
		String s=null;

			if(cards[index]%13 == 1)
			{
				s = "A";
			}
			else if(cards[index]%13 == 11)
			{
				s = "J";
			}
			else if(cards[index]%13 == 12)
			{
				s = "Q";
			}
			else if(cards[index]%13 == 0)
			{
				s = "K";
			}
			else
			{
				int n;
				n=cards[index]%13;
				s =""+n;
			}
		return s;
	}

	public String getSuitsNumbers(int index)
	{
		String s = "";

			if(cards[index]/13 == 0)
			{
				s = "S";
			}
			else if(cards[index]/13 == 1)
			{
				s = "H";
			}
			else if(cards[index]/13 == 2)
			{
				s = "D";
			}
			else
			{
				s ="C";
			}
		 return  s+getNumber(index);
	}

	public void printSuitsNumbers()
	{
		for(int i=0;i<num;i++)
		{
			System.out.print(getSuitsNumbers(i)+" ");
		}
		if(num == 0)
		{
			System.out.println(":なし");
		}
		else
		{
			System.out.println(":ツーペア");
		}
		System.out.println();
	}
   private void Sort()
   {
	   int n;
	   for(int i=0;i<num;i++)
	   {
		   if(cards[i] > cards[i+1])
		   {
			   n=cards[i];
			   cards[i]= cards[i+1];
			   cards[i+1]=n;
		   }
	   }
   }

   public void removeCard(int card)
   {
	   int index =-1;
	   for(int i = 0 ; i<num; i++)
	   {
		   if(cards[i] == card)
		   {
			   index =i;
		   }
	   }

		   if(index!=-1)
		   {
			   for(int i=index;i<num-1;i++)
			   {
				   cards[i] =cards[i+1];
			   }
			   num--;
		   }
	   }


   public int getNum()
   {
	   return num;
   }

   public String getName()
   {
	   return name;
   }

   public int judge()
   {

	   int number[] = new int[10];

		int suits[] = new int[5];

		int nduplicate[] = new int[13];

		int sduplicate[] = new int[4];

		int ccount[] = new int[5];

		int sflag = 0;



		for (int index = 0; index < this.num; index++)
		{
			if (getCard(index) < 1 || getCard(index) > 52)
			{

				this.name = "error";
				return 0;

			}
		}


		for (int index = 0; index < this.num; index++)
		{

			number[index] = (getCard(index) - 1) % 13;

		}


		for (int index = 0; index < this.num; index++)
		{
			suits[index] = getCard(index)/13;
			if (number[index] == 12)
				suits[index]--;
		}

		for (int i = 0; i < this.num; i++)
		{
			 nduplicate[number[i]]++;
		}

		for (int i = 0; i < this.num; i++)
		{
			 sduplicate[suits[i]]++;
		}

		for (int i = 0; i < 13; i++)
		{
			ccount[nduplicate[i]]++;
		}

		for (int i = 0; i < 4; i++)
		{
			if (sduplicate[i] == 5)
				sflag = 1;
		}


		if (nduplicate[0] == 1 && nduplicate[9] == 1 && nduplicate[10] == 1 && nduplicate[11] == 1 && nduplicate[12] == 1)
		{
			if (sflag== 1) {
				this.name = "RoyalStraightFlush";
				return 10000;
			}
		}

		for (int i = 0; i < 9; i++)
		{
			if (nduplicate[i] == 1 && nduplicate[i + 1] == 1 && nduplicate[i + 2] == 1 && nduplicate[i + 3] == 1
					&& nduplicate[i + 4] == 1)
				if (sflag== 1)
				{
					this.name = "StraightFlush";
					return 70;
				}
		}


		if (ccount[4] == 1)
		{
			this.name = "FourCard";
			return 40;
		}

		if (ccount[2] == 1 && ccount[3] == 1)
		{
			this.name = "FullHouse";
			return 20;
		}


		if (sflag ==1)
		{
			this.name = "Flush";
			return 10;
		}


		for (int i = 0; i < 9; i++)
		{
			if (nduplicate[i] == 1 && nduplicate[i + 1] == 1 && nduplicate[i + 2] == 1 && nduplicate[i + 3] == 1
					&& nduplicate[i + 4] == 1) {
				this.name = "Straight";
				return 5;
			}
		}

		if (nduplicate[0] == 1 && nduplicate[9] == 1 && nduplicate[10] == 1 && nduplicate[11] == 1 && nduplicate[12] == 1)
		{
			this.name = "Straight";
			return 5;
		}


		if (ccount[3] == 1)
		{
			this.name = "ThreeCard";
			return 3;
		}


		if (ccount[2] == 2)
		{
			this.name = "TwoPair";
			return 1;
		}


		if (ccount[2] == 1)
		{
			this.name = "OnePair";
			return 0;
		}

		else
		{
			this.name = "NoPair";
			return 0;
		}

   }

	public static void main(String args[])
	{

	}
}
