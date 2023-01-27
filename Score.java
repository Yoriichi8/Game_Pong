package Pong;
//*********************************
import java.awt.*;
import java.awt.event.*;

public class Score extends Rectangle{

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;
	
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	
	public void draw(Graphics g) {
		g.setFont(new Font("Consolas",Font.PLAIN,50));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.setColor(Color.blue);
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
		g.setColor(Color.pink);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
		
		gameOver(g,player1,player2);
	}
	
	public void gameOver(Graphics g, int player1, int player2) {
		
		//chi vincerà testo
		g.setFont(new Font("Consolas", Font.BOLD, 50));
		if(player1==11 && player2<11) {
			g.setColor(Color.GREEN);
			g.drawString(String.valueOf("Hai vinto"), (GAME_WIDTH-900), (GAME_HEIGHT/2));
			g.setColor(Color.RED);
			g.drawString(String.valueOf("Hai perso"), (GAME_WIDTH-350), (GAME_HEIGHT/2));
		}
		else if(player2==11 && player1<11)
		{
			g.setColor(Color.GREEN);
			g.drawString(String.valueOf("Hai vinto"), (GAME_WIDTH-350), (GAME_HEIGHT/2));
			g.setColor(Color.RED);
			g.drawString(String.valueOf("Hai perso"), (GAME_WIDTH-900), (GAME_HEIGHT/2));
		}
	}
}