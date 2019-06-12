/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.awt.Dimension;
import java.util.List;
import java.awt.Rectangle;
import java.util.Locale;
import java.util.Random;

import Type.Direction;
import Type.TankType;
import frame.GamePanel;
import util.ImageUtil;

public class Bot extends Tank {
		private Random random = new Random();  //随机坦克
		private Direction dir;                  // 方向
		private int fresh = GamePanel.FRESH;    
		private int MoveTimer = 0;              //移动计时器
public Bot(int x,int y, GamePanel gamePanel ,TankType type){
			super(x,y,ImageUtil.BOT_DOWN_IMAGE_URL,gamePanel,type);
				dir= Direction.DOWN;
			  setAttackCoolDowntime(1000);
			}


//移动
private Direction randomDirection(){  //随即位置
	int rum = random.nextInt(4);
	switch(rum){
	case 0:
		return Direction.UP;
	case 1:
		return Direction.RIGHT;
	case 2:
		return Direction.LEFT;
	default:
		return Direction.DOWN;
	}
}
// 碰撞
boolean hitTank(int x, int y){
	Rectangle next = new Rectangle(x,y,width,height);
	List<Tank> tanks =  gamePanel.getTanks();
	 for(int i = 0, lengh = tanks.size();i<lengh;i++){
		 Tank t = tanks.get(i);
		 if(!this.equals(t)){
			 if(t.alive &&t.hit(next) ){      //存疑？ 
				 if(t instanceof Bot ){
					 dir = randomDirection();
				 }
				 return true;
			 }
		 }
	 }
	 return false;
}
// 攻击
public void attack(){
	int rum = random.nextInt(100);
	if(rum <4){
		super.attack();
	}
}
//串联起来
public void go(){
	if(isAttackCoolDown()){
		attack();
	}
	if(MoveTimer >= 3000){
		dir =randomDirection();
		MoveTimer+=fresh;
	}else{
		MoveTimer +=fresh;
	}
	switch(dir){
	case UP:
		upward();
		break;
	case DOWN:
		downward();
		break;
	case RIGHT:
		rightward();
		break;
	case LEFT:
		leftward();
		break;
	
	}
	
}
}

