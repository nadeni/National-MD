package frame;

import Login_in.Open;
import Type.PropType;
import Type.TankType;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.JPanel;
import model.*;
import model.wall.Wall;
import util.ImageUtil;
import util.MapIO;

public class GamePanel extends JPanel implements KeyListener {

//    public int Green[][] = {{5, 10}, {400, 60}, {700, 100}};
//    public int Brick[][] = {{50, 30}, {70, 80}, {11, 12}};
//    public int Green[][] = MapIO.getGreen();
//    public int Brick[][] = MapIO.getBrick();
    public static final int FRESH = 20;
    private BufferedImage image;
    private Tank play;
    private boolean space_key, s_key, w_key, a_key, d_key;
    private int level;
    private List<Bullet> bullets;
    private volatile List<Tank> allTanks;
    private List<Tank> botTanks;
    private int botMaxInMap = 6;
    private int propMaxInMap = 2;
    private int landmineMaxInMap = 5;
    private final int botCount = 20;
    private int botReadyCount = botCount;
    private final int propCount = 2;
    private final int landmineCount = 5;
    private int propReadyCount = propCount;
    private int landmineReadyCount = landmineCount;
    private int botSurplusCount = botCount;
    private int propSurplusCount = propCount;
    private int landmineSurplusCount = landmineCount;
    private int botx[] = {10, 367, 754};
    private List<Tank> palyerTanks;
    private volatile boolean finish = false;
    private Base base;
    private Graphics2D g2;
    private MainFrame frame;
    private List<Wall> walls;
    private List<Boom> boomImage;
    private List<Prop> props;
    private List<Landmine> landmine;
    private Random r = new Random();
    private int createBotTimer = 0;
    private int createPropTimer = 0;
    private int createLandmineTimer = 0;
    private Tank survivor;

    public GamePanel(MainFrame frame, int level) {
        this.frame = frame;
        this.level = level;
        setBackground(Color.WHITE);
        init();
        Thread t = new FreshThead();
        t.start();
        addListener();
    }

    private void init() {
        bullets = new ArrayList<Bullet>();
        allTanks = new ArrayList<Tank>();
        walls = new ArrayList<Wall>();
        boomImage = new ArrayList<Boom>();
        props = new Vector<Prop>();
        landmine = new Vector<Landmine>();
        image = new BufferedImage(794, 572, BufferedImage.TYPE_INT_BGR);
        g2 = image.createGraphics();

        palyerTanks = new ArrayList<Tank>();
        play = new Tank(278, 537, ImageUtil.PYAYER_UP_IMAGE_URL, this, TankType.player);

        palyerTanks.add(play);
        botTanks = new Vector<Tank>();
        botTanks.add(new Bot(botx[0], 1, this, TankType.bot));
        botTanks.add(new Bot(botx[1], 1, this, TankType.bot));
        botTanks.add(new Bot(botx[2], 1, this, TankType.bot));
        botReadyCount -= 3;
        allTanks.addAll(palyerTanks);
        allTanks.addAll(botTanks);
        base = new Base(367, 532);
        initWalls();
    }

    private void addListener() {
        frame.addKeyListener(this);
    }

    private void initWalls() {
        Map map = Map.getMap(level);
        walls.addAll(map.getWalls());
        walls.add(base);
    }

    @Override
    public void paint(Graphics g) {
        paintTankActoin();
        CreateBot();
        CreateProp();
        paintImage();
        CreateLandmine();
        g.drawImage(image, 0, 0, this);
    }

    private void paintImage() {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        panitBoom();
        paintBotCount();
        panitBotTanks();
        panitPlayerTanks();
        allTanks.addAll(palyerTanks);
        allTanks.addAll(botTanks);
        panitWalls();
        panitBullets();
        panitProps();
        panitLandmine();

        if (botSurplusCount == 0) {
            stopThread();
            paintBotCount();
            g2.setFont(new Font("楷体", Font.BOLD, 100));
            g2.setColor(Color.green);
            g2.drawString("victory !", 250, 400);
            if (Level.LevelTest()) {
                gotoNextLevel();
            } else {
                gotoAllpassPanel();
            }

        }

        if (!play.isAlive()) {
            stopThread();
            boomImage.add(new Boom(play.x, play.y));
            panitBoom();
            paintGameOver();
            keepfile(level);
            gotoPrevisousLevel();
            
        }

        if (!base.isAlive()) {
            stopThread();
            paintGameOver();
            base.setImage(ImageUtil.BREAK_BASE_IMAGE_URL);
            gotoPrevisousLevel();
        }
        g2.drawImage(base.getImage(), base.x, base.y, this);
    }

    private void paintBotCount() {
        g2.setColor(Color.BLUE);
        g2.drawString("How many more enemies are there：" + botSurplusCount, 337, 15);
    }

    private void paintGameOver() {
        g2.setFont(new Font("楷体", Font.BOLD, 100));
        g2.setColor(Color.RED);
        g2.drawString("Game Over !", 250, 400);

    }

    private void panitProps() {
        for (int i = 0; i < props.size(); i++) {
            Prop p = props.get(i);
            if (p.isAlive()) {
                g2.drawImage(p.getImage(), p.x, p.y, this);
                p.hitTank();
            } else {
                props.remove(i);
                i--;
                decreaseProp();
            }
        }
    }

    private void panitLandmine() {
        for (int i = 0; i < landmine.size(); i++) {
            Landmine l = landmine.get(i);
            if (l.isAlive()) {
                g2.drawImage(l.getImage(), l.x, l.y, this);
                l.hitTank();
            } else {
                landmine.remove(i);
                i--;
                decreaseLandmine();
            }
        }
    }

    private void CreateProp() {
        createPropTimer += FRESH;
        if (props.size() < propMaxInMap && propReadyCount > 0 && createPropTimer >= 9000) {
            int t = r.nextInt(2);
            int x = r.nextInt(700);
            int y = r.nextInt(500);
            if (t == 1) {
                Rectangle propnRect = new Rectangle(x, y, 15, 75);
                for (int i = 0, lengh = props.size(); i < lengh; i++) {
                    Prop p = props.get(i);
                    if (p.isAlive() && p.hit(propnRect)) {
                        return;
                    }
                }
                props.add(new Prop(x, y, GamePanel.this, PropType.prop_speed));
                propReadyCount--;
            }
            createPropTimer = 0;
        }
    }

    private void CreateLandmine() {
        createLandmineTimer += FRESH;

        if (landmine.size() < landmineMaxInMap && landmineReadyCount > 0 && createLandmineTimer >= 8000) {
            int t = r.nextInt(2);
            int x = r.nextInt(700);
            int y = r.nextInt(500);
            if (t == 1) {
                Rectangle propnRect = new Rectangle(x, y, 50, 40);
                for (int i = 0, lengh = landmine.size(); i < lengh; i++) {
                    Landmine p = landmine.get(i);
                    if (p.isAlive() && p.hit(propnRect)) {
                        return;
                    }
                }
                landmine.add(new Landmine(x, y, GamePanel.this));
                landmineReadyCount--;
            }
            createLandmineTimer = 0;
        }
    }

    private void panitBoom() {
        for (int i = 0; i < boomImage.size(); i++) {
            Boom boom = boomImage.get(i);
            if (boom.isAlive()) {
                boom.show(g2);
            } else {
                boomImage.remove(i);
                i--;
            }
        }
    }

    private void panitWalls() {
        for (int i = 0; i < walls.size(); i++) {
            Wall w = walls.get(i);
            if (w.isAlive()) {
                g2.drawImage(w.getImage(), w.x, w.y, this);
            } else {
                walls.remove(i);
                i--;
            }
        }
    }

    private void panitBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if (b.isAlive()) {
                b.move();
                b.hitBase();
                b.hitWall();
                b.hitTank();
                g2.drawImage(b.getImage(), b.x, b.y, this);
            } else {
                bullets.remove(i);
                i--;
            }
        }
    }

    /**
     * 绘制电脑坦克
     */
    private void panitBotTanks() {
        for (int i = 0; i < botTanks.size(); i++) {
            Bot t = (Bot) botTanks.get(i);
            if (t.isAlive()) {
                t.go();
                g2.drawImage(t.getImage(), t.x, t.y, this);
            } else {
                botTanks.remove(i);
                i--;
                boomImage.add(new Boom(t.x, t.y));
                decreaseBot();
            }
        }
    }

    /**
     * 绘制玩家坦克
     */
    private void panitPlayerTanks() {
        for (int i = 0; i < palyerTanks.size(); i++) {
            Tank t = palyerTanks.get(i);
            if (t.isAlive()) {
                g2.drawImage(t.getImage(), t.x, t.y, this);
            } else {
                palyerTanks.remove(i);
                i--;
                boomImage.add(new Boom(t.x, t.y));
            }
        }
    }

    /**
     * 结束游戏帧刷新
     */
    private synchronized void stopThread() {
        frame.removeKeyListener(this);
        finish = true;
    }

    private void keepfile(int level) {

    }

    /**
     * 游戏帧刷新线程
     */
    private class FreshThead extends Thread {

        @Override
        public void run() {
            while (!finish) {
                repaint();
                try {
                    Thread.sleep(FRESH);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }

    private void CreateBot() {
        createBotTimer += FRESH;
        if (botTanks.size() < botMaxInMap && botReadyCount > 0 && createBotTimer >= 4000) {
            int index = r.nextInt(3);
            Rectangle bornRect = new Rectangle(botx[index], 1, 35, 35);
            for (int i = 0, lengh = allTanks.size(); i < lengh; i++) {
                Tank t = allTanks.get(i);
                if (t.isAlive() && t.hit(bornRect)) {
                    return;
                }
            }
            botTanks.add(new Bot(botx[index], 1, GamePanel.this, TankType.bot));
            botReadyCount--;
            createBotTimer = 0;
        }
    }

    /**
     * * 进入下一关卡
     */
    private void gotoNextLevel() {
        Thread jump = new JumpPageThead(Level.nextLevel());
        jump.start();
    }

    private void gotoPrevisousLevel() {
        Thread jump = new JumpPageThead(Level.previsousLevel());
        jump.start();

    }

    public void decreaseBot() {
        botSurplusCount--;
    }

    public void decreaseProp() {
        propSurplusCount--;
    }

    private void decreaseLandmine() {
        landmineSurplusCount--;
    }

    public List<Prop> getProps() {
        return props;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                space_key = true;
                break;
            case KeyEvent.VK_W:
                w_key = true;
                a_key = false;
                s_key = false;
                d_key = false;
                break;
            case KeyEvent.VK_A:
                w_key = false;
                a_key = true;
                s_key = false;
                d_key = false;
                break;
            case KeyEvent.VK_S:
                w_key = false;
                a_key = false;
                s_key = true;
                d_key = false;
                break;
            case KeyEvent.VK_D:
                w_key = false;
                a_key = false;
                s_key = false;
                d_key = true;
                break;

        }
    }

    private void paintTankActoin() {
        if (space_key) {
            play.attack();
        }
        if (w_key) {
            play.upward();
        }
        if (d_key) {
            play.rightward();
        }
        if (a_key) {
            play.leftward();
        }
        if (s_key) {
            play.downward();
        }

    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                space_key = false;
                break;
            case KeyEvent.VK_W:
                w_key = false;
                break;
            case KeyEvent.VK_A:
                a_key = false;
                break;
            case KeyEvent.VK_S:
                s_key = false;
                break;
            case KeyEvent.VK_D:
                d_key = false;
                break;

        }
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public Base getBase() {
        return base;
    }

    public List<Tank> getTanks() {
        return allTanks;
    }

    private class JumpPageThead extends Thread {

        int level;

        public JumpPageThead(int level) {
            this.level = level;
        }

        public void run() {
            try {
                Thread.sleep(1000);
                frame.setPanel(new LevelPanel(level, frame));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }
    private void gotoAllpassPanel() {
        frame.setPanel(new AllPassPanel(frame));
    }
}
