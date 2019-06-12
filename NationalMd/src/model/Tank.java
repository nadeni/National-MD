
package model;

import java.util.List;



import java.awt.Point;
import java.awt.Rectangle;

import Type.Direction;
import Type.TankType;
import frame.GamePanel;
import model.wall.GrassWall;
import model.wall.Wall;
import util.ImageUtil;

public class Tank extends VisibleImage {
		GamePanel gamePanel;
		Direction direction ;
		protected boolean alive = true;    
		private int speed = 3;           
		private boolean attackCoolDown = true; 
		private int attackCoolDowntime = 500;   
		TankType type;											
		private String upImage;
		private String downImage;
		private String rightImage;
		private String leftImage;	

public Tank(int x, int y,String url ,GamePanel gamePanel ,TankType type){
	super(x,y,url);
	this.gamePanel = gamePanel;
	this.type =type;
	direction = Direction.UP;
	switch(type){
	case player: 
		upImage = ImageUtil.PYAYER_UP_IMAGE_URL;
		downImage = ImageUtil.PYAYER_DOWN_IMAGE_URL;
		leftImage = ImageUtil.PYAYER_LEFT_IMAGE_URL;
		rightImage = ImageUtil.PYAYER_RIGHT_IMAGE_URL;
		break;
	case bot: 
		upImage = ImageUtil.BOT_UP_IMAGE_URL;
		downImage = ImageUtil.BOT_DOWN_IMAGE_URL;
		leftImage = ImageUtil.BOT_LEFT_IMAGE_URL;
		rightImage = ImageUtil.BOT_RIGHT_IMAGE_URL;
	
	}
}
public boolean isAttackCoolDown() {
	return attackCoolDown;
}
public void setAttackCoolDown(boolean attackCoolDown) {
	this.attackCoolDown = attackCoolDown;
}
/*撞墙了没********************************************************************************************/
private boolean hitWall(int x,int y){
	Rectangle next = new Rectangle(x,y,width,height);
	List <Wall> walls = gamePanel.getWalls();
	for(int i= 0,lengh = walls.size();i<lengh;i++){
	Wall w = walls.get(i);
	if(w instanceof GrassWall){
		continue;
	}else if(w.hit(next)){
		return true;
	}
	}
	return false;
	
}
/*装坦克了没***********************************************************************************************/
private boolean hitTank(int x,int y){
	Rectangle next = new Rectangle(x,y,width,height);
	List<Tank> tanks = gamePanel.getTanks();
	for(int i =0 ,length = tanks.size();i<length;i++){
		Tank t = tanks.get(i);
		if(! this.equals(t)){
			if(t.alive&&t.hit(next)){
				return true;
			}
		}
	}
	return false;
}
/*撞边界了没*******************************************************************************************8**/
protected void moveToBorder(){
	if(x<0){
		x=0;
	}else if(x> gamePanel.getWidth()-width){
		x= gamePanel.getWidth()-width;
	}
	if(y<0){
		y =0;
	}else if(y>gamePanel.getHeight() -height){
		y = gamePanel.getHeight()-height;
	}
}
//移动******************************************************************************************
public void leftward(){
	if(direction != Direction.LEFT){
		setImage(leftImage);
	}
	direction = Direction.LEFT;
	if(!hitWall(x-speed,y)&&!hitTank(x-speed,y)){
		x -= getSpeed();
		moveToBorder();
	}
}
public void rightward(){
	if(direction != Direction.RIGHT){
		setImage(rightImage);
	}
	direction = Direction.RIGHT;
	if(!hitWall(x+speed,y)&&!hitTank(x+speed,y)){
		x += getSpeed();
		moveToBorder();
	}
}
public void upward(){
	if(direction != Direction.UP){
		setImage(upImage);
	}
	direction = Direction.UP;
	if(!hitWall(x,y-speed)&&!hitTank(x,y-speed)){
		y -= getSpeed();
		moveToBorder();
	}
}
public void downward(){
	if(direction != Direction.DOWN){
		setImage(downImage);
	}
	direction = Direction.DOWN;
	if(!hitWall(x,y+speed)&&!hitTank(x,y+speed)){
		y += getSpeed();
		moveToBorder();
	}
}

 
//21***攻击************************************************************************************************/
private class AttackCD extends Thread{
		public void run(){
			attackCoolDown = false;
			try{
				Thread.sleep(attackCoolDowntime);
				
			}catch(InterruptedException e){
				System.out.println(e);
				
			}
		attackCoolDown = true;
		}
}
public void attack(){
	if (attackCoolDown) {
		Point p = getHeadPoint();
		Bullet b = new Bullet(p.x - Bullet.LENGTH / 2, p.y - Bullet.LENGTH / 2, direction, gamePanel, type);
		gamePanel.addBullet(b);
		new AttackCD().start();
		
	}
}
public boolean isAlive() {
	return alive;
}
public void setAlive(boolean alive) {
	this.alive = alive;
}
public int getAttackCoolDowntime() {
	return attackCoolDowntime;
}
public void setAttackCoolDowntime(int attackCoolDowntime) {
	this.attackCoolDowntime = attackCoolDowntime;
}private Point getHeadPoint() {
	Point p = new Point();
	switch (direction) {
	case UP:
		p.x = x + width / 2;
		p.y = y;
		break;
	case DOWN:
		p.x = x + width / 2;
		p.y = y + height;
		break;
	case RIGHT:
		p.x = x + width;
		p.y = y + height / 2;
		break;
	case LEFT:
		p.x = x;
		p.y = y + height / 2;
		break;
	default:
		p = null;
	}
	return p;
}

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public  void setSpeed(int speed) {
        this.speed = speed;
    }

}
		
