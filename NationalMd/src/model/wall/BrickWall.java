/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.wall;
import util.ImageUtil;

/**
 *
 * @author 皮皮
 */
public class BrickWall extends Wall{

    /**
     *
     * @param x
     * @param y
     */
    public BrickWall(int x,int y){
		super(x,y,ImageUtil.BRICKWALL_IMAGE_URL);  //  
	}
}
