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

import javax.swing.JPanel;
import model.Level;

public class LevelPanel extends JPanel {

    private int level;
    private MainFrame frame;
    private String levelStr;
    private String ready = "";

    public LevelPanel(int level, MainFrame frame) {

        this.frame = frame;
        this.level = level;
        levelStr = "Level " + level;
        Thread t = new LevelPanelThread();
        t.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(new Font("Consolas", Font.BOLD, 50));
        g.setColor(Color.BLACK);
        g.drawString(levelStr, 260, 300);
        g.setColor(Color.RED);
        g.drawString(ready, 270, 400);
    }

    private void gotoGamePanel() {
        frame.setPanel(new GamePanel(frame, level));
    }

    private class LevelPanelThread extends Thread {

        public void run() {
            for (int i = 0; i < 6; i++) {
                if (i % 2 == 0) {
                    levelStr = "Level " + level;
                } else {
                    levelStr = "";
                }
                if (i == 4) {
                    ready = "Ready? !";
                }
                repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
            gotoGamePanel();
        }
    }
}
