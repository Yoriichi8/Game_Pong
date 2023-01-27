package Pong;
//*********************************

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 20;
	static final int PADDLE_HEIGHT = 100;
	
	//-------------------------------------------------------------------------
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	//-------------------------------------------------------------------------
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	//-------------------------------------------------------------------------
	
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	
	
	public void newPaddles() {
		paddle1 = new Paddle(10,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle((GAME_WIDTH-PADDLE_WIDTH)-10,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	
	
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		
	//-----------------------------------------
		Toolkit.getDefaultToolkit().sync();
	//-----------------------------------------
	}
	
	
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	
	public void checkCollision() {
		
		//-------rimbalzo della pallina contro i bordi della finestra---------
		if(ball.y <=0) {
			ball.setYDirection(-ball.dy);
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.dy);
		}
		
		//-------rimbalzo della pallina contro i paddle--------
		if(ball.intersects(paddle1)) {
			ball.dx = Math.abs(ball.dx);
			ball.dx++; //incrementa la velocità della pallina
			if(ball.dy>0)
				ball.dy++; 
			else
				ball.dy--;
			ball.setXDirection(ball.dx);
			ball.setYDirection(ball.dy);
		}
		if(ball.intersects(paddle2)) {
			ball.dx = Math.abs(ball.dx);
			ball.dx++; //incrementa la velocità della pallina
			if(ball.dy>0)
				ball.dy++; 
			else
				ball.dy--;
			ball.setXDirection(-ball.dx);
			ball.setYDirection(ball.dy);
		}
		
		//----------paddle contro i lati della finestra-----------
		if(paddle1.y<=0)
			paddle1.y=0;
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(paddle2.y<=0)
			paddle2.y=0;
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		
		//------da il punto e ricrea il gioco----------------
		if(ball.x <=0) {
			score.player2++;
			newBall();
			System.out.println("Player 2: "+score.player2);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
			newBall();
			System.out.println("Player 1: "+score.player1);
		}
		
		//-------fine------------------------------------
		if(score.player1==12 && score.player2<12) {
			score.player1 = 0;
			score.player2 = 0;
			newPaddles();
			newBall();
			System.out.println("Player 1: "+score.player1);
			System.out.println("Player 2: "+score.player2);
		}
		else if(score.player2==12 && score.player1<12) {
			score.player1 = 0;
			score.player2 = 0;
			newPaddles();
			newBall();
			System.out.println("Player 1: "+score.player1);
			System.out.println("Player 2: "+score.player2);
		}
	}
	
	
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double FPS =60.0;
		double duration = 1000000000 / FPS;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/duration;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}

