/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;
import java.util.List;
import model.wall.BrickWall;
import model.wall.Wall;
import util.MapIO;


public class Map {
	private static List<Wall> walls = new ArrayList<Wall>();
	private Map() {

	}
	public static Map getMap(String level) {
		walls.clear();
		walls.addAll(MapIO.readMap(level));
		// 基地砖墙
		for (int a = 347; a <= 407; a += 20) {
			for (int b = 512; b <= 572; b += 20) {
				if (a >= 367 && a <= 387 && b >= 532) {
				} else {
					walls.add(new BrickWall(a, b));
				}
			}
		}
		return new Map();
	}

	
	public static Map getMap(int level) {
		return getMap(String.valueOf(level));
	}

	
	public List<Wall> getWalls() {
		return walls;
	}

}