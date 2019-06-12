/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public abstract class VisibleImage {
		public int x;
		public int y;
		int width ;
		int height ;  
		BufferedImage image;
public VisibleImage(int x,int y ,int width ,int height){
	this.x = x;
	this.y = y;
	this.width =width;
	this.height = height;
	image = new  BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
}

public VisibleImage(int x ,int y ,String url){	
	this.x = x;
	this.y = y;
	try{
		image = ImageIO.read(new File(url));
		this.width = image.getWidth();
		this.height = image.getHeight();
	}catch(IOException e ){
	   System.out.println(e);
	}
}
public BufferedImage getImage() {
	return image;
}
public void setImage(String url){
	try{
		this.image  = ImageIO.read(new File(url));
	}catch(IOException e){
		System.out.println(e);
	}
	
}
public boolean hit(VisibleImage v) {
	return hit(v.getBounds());
}


public boolean hit(Rectangle r) {
	if (r == null) {
		return false;
	}
	return getBounds().intersects(r);
}


public Rectangle getBounds() {
	return new Rectangle(x, y, width, height);
}


public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public int getHeight() {
	return height;
}

public void setHeight(int height) {
	this.height = height;
}
                @Override
                public String toString() {
	return "Visiblemage [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
}

}
