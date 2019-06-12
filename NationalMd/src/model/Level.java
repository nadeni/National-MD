/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Login_in.Open;
import java.io.File;
import java.io.FileNotFoundException;

import util.MapIO;

public class Level {

    private static int Level = 1;
    private static int count=0;

    static {
        try {
            File f = new File(MapIO.DATA_PATH);
            if (!f.exists()) {
                throw new FileNotFoundException("地图文件缺失！");
            }
            File fs[] = f.listFiles();// 
            count = fs.length;
            if (count == 0) {
                throw new FileNotFoundException("地图文件缺失！");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
public static boolean LevelTest(){
            if (Level >= count) {
            return false;
        }
            return true;
}
    public static int nextLevel() {
       Level++;
        return Level;// 返回下一关的值
    }

    public static int previsousLevel() {
        return Level;
    }

    public static int getprevisousLevel() {
        return Level;
    }

    public static void setlive(int a) {
        Level = a;
    }
}
