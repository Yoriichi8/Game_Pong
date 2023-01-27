package Pong;
//*********************************

import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

	Random random;
	int dx;
	int dy;
	int initialSpeed = 2;
	
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		
		int vectorX = random.nextInt(2);
		if(vectorX == 0)
			vectorX--;
		setXDirection(vectorX*initialSpeed);
		
		int vectorY = random.nextInt(2);
		if(vectorY == 0)
			vectorY--;
		setYDirection(vectorY*initialSpeed);
		
	}
	
	public void setXDirection(int vectorX) {
		dx = vectorX;
	}
	public void setYDirection(int vectorY) {
		dy = vectorY;
	}
	public void move() {
		x += dx;
		y += dy;
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}