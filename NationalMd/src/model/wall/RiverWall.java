/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.wall;

import util.ImageUtil;

//河流

/**
 *
 * @author 皮皮
 */
public class RiverWall extends Wall {
	public RiverWall(int x, int y){
		super(x,y,ImageUtil.RIVERWALL_IMAGE_URL);
	}
}
