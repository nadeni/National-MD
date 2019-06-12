/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.wall;

import util.ImageUtil;

//草地
public class GrassWall extends Wall {
	public GrassWall(int x,int y){
		super(x,y,ImageUtil.GRASSWALL_IMAGE_URL);
	}
}

