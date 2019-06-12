package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import Type.Direction;
import Type.TankType;
import frame.GamePanel;
import model.wall.BrickWall;
import model.wall.IronWall;
import model.wall.Wall;

//子弹
public class Bullet extends VisibleImage{
   Direction direction;
   static final int LENGTH =8;	
   private GamePanel gamePanel ;  
   private int speed = 7 ;    
   private boolean alive = true ;
   Color color = Color.ORANGE ;
   TankType owner ;            
private void init(){ 
	Graphics g = image.getGraphics();
	g.setColor(Color.WHITE );
	g.fillRect(0, 0, LENGTH, LENGTH);
	g.setColor(color);
	g.fillOval(0, 0, LENGTH, LENGTH);
	g.setColor(Color.BLACK);
	g.drawOval(0, 0, LENGTH-1, LENGTH-1);
}
public Bullet(int x,int y,Direction direction ,GamePanel gamePanel ,TankType owner){
	super(x,y,LENGTH,LENGTH);
	this.direction =direction;
	this.gamePanel = gamePanel;
	this.owner = owner;
	init();
}
//移动
private void moveToBorder() {
	if(x<0){
		dispose();
	}else if(x> gamePanel.getWidth()-width){
		dispose();
	}
	if(y<0){
		dispose();
	}else if(y>gamePanel.getHeight() -height){
		dispose();
	}	
}
private void leftward(){
	x -=speed;
	moveToBorder();
}

private void rightward(){
	x +=speed;
	moveToBorder();
}
private void upward(){
	y -=speed;
	moveToBorder();
}
private void downward(){
	y +=speed;
	moveToBorder();
}
public void move(){
	switch(direction){
	case UP:
		 upward();
		 break;
	case DOWN:
		downward();
		break;
	case RIGHT:
		rightward();
		break;
	case LEFT:{
		leftward();
		break;
	}
	}
}private synchronized void dispose() {
	alive = false;
}public boolean isAlive() {
	return alive;
}public void hitBase() {
	Base b = gamePanel.getBase();
	if (this.hit(b)) {
		b.setAlive(false);
	}
}public void hitTank() {
	List<Tank> tanks = gamePanel.getTanks();
	for (int i = 0, lengh = tanks.size(); i < lengh; i++) {
		Tank t = tanks.get(i);
		if (t.isAlive() && this.hit(t)) {
			switch (owner) {
			case player:// 如果是玩家
				if (t instanceof Bot) {// 如果击中的坦克是电脑
					alive = false;
					t.setAlive(false);
				}
				break;
			case bot:
				if (t instanceof Bot) {
					alive = false;
				} else if (t instanceof Tank) {// 如果击中的是玩家
					alive = false;
					t.setAlive(false);
				}
				break;
			default:
				alive = false;
				t.setAlive(false);
			}
		}
	}
}


public void hitWall() {
	List<Wall> walls = gamePanel.getWalls();
	for (int i = 0, lengh = walls.size(); i < lengh; i++) {
		Wall w = walls.get(i);
		if (this.hit(w)) {
			if (w instanceof BrickWall) {
				alive = false;
				w.setAlive(false);
			}
			if (w instanceof IronWall) {
				alive = false;
			}
		}
	}
}

}
