/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import Login_in.Open;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import model.Level;
import static model.Level.getprevisousLevel;

public class LoginPanel extends JPanel implements KeyListener {

    private MainFrame frame;// 主窗体
    private int y1 = 370, y2 = 430;
    private int tankY = y1;

    public LoginPanel(MainFrame frame) {
        this.frame = frame;
        addListener();
    }

    @Override
    public void paint(Graphics g) {

        Font font = new Font("黑体", Font.BOLD, 35);
        g.setFont(font);
        g.setColor(Color.BLUE);
        g.drawString("PLEASE PRESS SPACE....", 200, 300);

    }

    private void gotoLevelPanel() {
        frame.removeKeyListener((KeyListener) this);
        frame.setPanel(new LevelPanel(getprevisousLevel(), frame));

    }
//    private void gotoAllpassPanel() {
//        frame.setPanel(new AllPassPanel(frame));
//    }
    private void addListener() {
        frame.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {

            case KeyEvent.VK_SPACE:
                gotoLevelPanel();
//                gotoAllpassPanel();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
