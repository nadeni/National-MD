/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.wall;

import model.VisibleImage;


public abstract class Wall extends VisibleImage {
	private boolean alive = true;


	public Wall(int x, int y, String url) {
		super(x, y, url);
	}

	
	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	
	public boolean equals(Object obj) {
		if (obj instanceof Wall) {
			Wall w = (Wall) obj;
			if (w.x == x && w.y == y) {
				return true;
			}
		}
		return super.equals(obj);
	}
}
