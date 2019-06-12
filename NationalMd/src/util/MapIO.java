/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import Type.WallType;
import model.wall.*;

public class MapIO {

    /*设置地图和找到地图**/
    public final static String DATA_PATH = "src/map/";
    public final static String IMAGE_PATH = "src/photo/";
    public final static String DATA_SUFFIX = ".map";
    public final static String IMAGE_SUFFIX = ".jpg";
    private static int[][] Green = new int[2][];
    private static int[][] Brick = new int[2][];

    public static List<Wall> readMap(String mapName) {
        File file = new File(DATA_PATH + mapName + DATA_SUFFIX);
        return readMap(file);
    }

    public static List<Wall> readMap(File file) {
        Properties pro = new Properties();
        List<Wall> walls = new ArrayList<Wall>();
        try {

            pro.load(new FileInputStream(file));
            String brickStr = (String) pro.get(WallType.brick.name());
            String grassStr = (String) pro.get(WallType.grass.name());
            String riverStr = (String) pro.get(WallType.river.name());
            String ironStr = (String) pro.get(WallType.iron.name());
            if (brickStr != null) {
                walls.addAll(readWall(brickStr, WallType.brick));
            }
            if (grassStr != null) {
                walls.addAll(readWall(grassStr, WallType.grass));
            }
            if (riverStr != null) {
                walls.addAll(readWall(riverStr, WallType.river));
            }
            if (ironStr != null) {
                walls.addAll(readWall(ironStr, WallType.iron));
            }

            return walls;// 返回总墙块集合

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Wall> readWall(String data, WallType type) {

        String walls[] = data.split(";");
        Wall wall;
        List<Wall> w = new LinkedList<Wall>();// 创建墙块集合
        switch (type) {
            case brick:// 如果是砖墙
                for (int i = 0; i <walls.length; i++) {// 遍历分割结果
                    String axes[] = walls[i].split(",");// 使用“,”分割字符串                 
                    wall = new BrickWall(Integer.parseInt(axes[0]),Integer.parseInt(axes[1]));// 创建砖墙对象
                    w.add(wall);// 添加此墙块
                }


                break;
            case river:// 如果是河流
                for (String wStr : walls) {
                    String axes[] = wStr.split(",");
                    wall = new RiverWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 创建河流对象
                    w.add(wall);
                }
                break;
            case grass:// 如果是草地
                for (String wStr : walls) {
                    String axes[] = wStr.split(",");
                    wall = new GrassWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 创建草地对象
                    w.add(wall);

                }
                break;
            case iron:// 如果是铁墙
                for (String wStr : walls) {
                    String axes[] = wStr.split(",");

                    wall = new IronWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// 创建铁墙对象
                    w.add(wall);
                }
                break;
        }
        return w;// 返回墙块集合

    }

    /**
     * @return the Green
     */
    public static int[][] getGreen() {
        return Green;
    }

    /**
     * @param aGreen the Green to set
     */
    public static void setGreen(int[][] aGreen) {
        Green = aGreen;
    }

    /**
     * @return the Brick
     */
    public static int[][] getBrick() {
        return Brick;
    }

    /**
     * @param aBrick the Brick to set
     */
    public static void setBrick(int[][] aBrick) {
        Brick = aBrick;
    }
}
