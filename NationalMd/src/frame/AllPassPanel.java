/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author 皮皮
 */
public class AllPassPanel extends JPanel {
        private MainFrame frame;// 主窗体
    public AllPassPanel(MainFrame frame) {
        this.frame = frame;
    }
        @Override
        public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(new Font("Consolas", Font.BOLD, 50));
        g.setColor(Color.RED);
        g.drawString("Your win the Game", 170, 450);
            try {
                Image image=ImageIO.read(new File("src/photo/victory.jpg"));
                g.drawImage(image, 150, 50, null);
            } catch (IOException ex) {
                Logger.getLogger(AllPassPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
