package card;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PokerApp
 */
@WebServlet("/PokerApp")
public class PokerApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PokerApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		Player player = (Player)session.getAttribute("player");
		if(player ==null)
		{
			player = new Player();
			player.setName("");
			player.setCoins(0);
			session.setAttribute("player",player);
		}
		if(player.getCoins() ==0)
		{
			player.setName("工大太郎");
			player.setCoins(	10);
		}

		int c = player.getCoins();
		player.setCoins(c-1);
		CardDeck cardDeck = new CardDeck();
		cardDeck.shuffle();
		Cards cards = new Cards(5, "test");
		for (int i=0; i<5; i++) {
		int card = cardDeck.getNextCard();
		cards.addCard(card);
		}

		session.setAttribute("cardDeck", cardDeck);
		session.setAttribute("cards", cards);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pokerChange.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		CardDeck cardDeck = (CardDeck)session.getAttribute("cardDeck"); // カードデッキ
		Cards cards = (Cards)session.getAttribute("cards"); // 5枚の手札
		Player player = (Player)session.getAttribute("player"); // プレイヤー情報

		String a[]= request.getParameterValues("ccard");

	if(a!=null) {
		for(int i=0;i<a.length;i++)
		{

			System.out.print(" "+a[i]);// (課題3.1)交換するカード番号(1～52)をSystem.out.println()する
			Integer b = Integer.valueOf(a[i]);
		    cards.removeCard(b) ;// (課題3.2)手札から交換するカードを取り除く
		    int card = cardDeck.getNextCard();//(3.3)
			cards.addCard(card);// (課題3.3)取り除いた分をカードデッキから引き，手札に加える

		}
		 System.out.println();
	}
		int reward = cards.judge();
		int c = player.getCoins();
		if(reward ==1)
		{
			player.setCoins(c+1);
		}
		else if(reward ==2)
		{
			player.setCoins(c+2);
		}
		else if(reward ==3)
		{
			player.setCoins(c+3);
		}
		else if(reward ==5)
		{
			player.setCoins(c+5);
		}
		else if(reward ==10)
		{
			player.setCoins(c+10);
		}
		else if(reward ==20)
		{
			player.setCoins(c+20);
		}
		else if(reward ==40)
		{
			player.setCoins(c+40);
		}
		else if(reward ==70)
		{
			player.setCoins(c+70);
		}
		else if(reward ==10000)
		{
			player.setCoins(c+10000);
		}
		// pokerResult.jspにフォワードして，手札を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pokerResult.jsp");
		dispatcher.forward(request, response);
	}
}
