package model;

import Type.PropType;
import frame.GamePanel;
import java.util.List;
import util.ImageUtil;

public class Prop extends VisibleImage {
    
    GamePanel gamePanel;
    private boolean alive = true; // 活着的？					
    PropType type;
    private String PROP_SPEED;
    private String PROP_MOTHERFUCKER;
    
    public Prop(int x, int y, GamePanel gamePanel, PropType type) {
        super(x, y, ImageUtil.PROP_SPEED);
        this.gamePanel = gamePanel;
        this.type = type;
        switch (type) {
            case prop_speed:
                PROP_SPEED = ImageUtil.PROP_SPEED;
                break;
            case prop_motherfucker:
                PROP_MOTHERFUCKER = ImageUtil.PROP_MOTHERFUCKER;
            
        }
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public void hitTank() {
        List<Tank> tanks = gamePanel.getTanks();
        for (int i = 0, lengh = tanks.size(); i < lengh; i++) {
            final Tank t = tanks.get(i);
            if (t.isAlive() && this.hit(t)) {
                if (t instanceof Bot) {
                } else if (t instanceof Tank) {
                    alive=false;
                    t.setSpeed(6);
                }
            }
        }
    }
}
