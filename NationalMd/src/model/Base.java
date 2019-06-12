/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import model.wall.Wall;
import util.ImageUtil;

public class Base extends Wall {
	private boolean alive = true;
        @Override
	public boolean isAlive() {
		return alive;
	}
        @Override
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public Base(int x,int y){
		super(x,y,ImageUtil.BASE_IMAGE_URL);
	}
	
}
