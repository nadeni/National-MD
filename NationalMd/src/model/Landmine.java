package model;
import frame.GamePanel;
import java.util.List;
import model.wall.Wall;
import util.ImageUtil;
public class Landmine extends Wall {

    private boolean alive = true;
    GamePanel gamePanel;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Landmine(int x, int y,GamePanel gamePanel) {
        super(x, y, ImageUtil.LANDMINE_IMAGE_URL);
        this.gamePanel=gamePanel;
    }

    public void hitTank() {
        List<Tank> tanks = gamePanel.getTanks();
        for (int i = 0, lengh = tanks.size(); i < lengh; i++) {
            Tank t = tanks.get(i);
            if (t.isAlive() && this.hit(t)) {
                if(t instanceof Bot){
                }else if(t instanceof Tank) {
                    alive = false;
                    t.setAlive(false);
                } 
            } 
        }
    }
}
