/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import Login_in.Connective;
import Login_in.Open;
import Login_in.welcome;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Level;

public class MainFrame extends JFrame {

    private static Connection  con = null;
    private static PreparedStatement pst;

    public MainFrame() {
        setTitle("NationalMD");
        setSize(800, 600);
        setResizable(false);
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension d = tool.getScreenSize();

        setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addListener();
        setPanel(new LoginPanel(this));
    }
    

    private void addListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int closeCode = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want exit", "Warning！", JOptionPane.YES_NO_OPTION);// 弹出选择对话框，并记录用户选择
                if (closeCode == JOptionPane.YES_OPTION) {
                    dispose();
                    keepdata();
                    Open.W(welcome.getUser());
                }
            }
        });
    }

    public void setPanel(JPanel panel) {
        Container c = getContentPane();
        c.removeAll();
        c.add(panel);
        c.validate();
    }

    public static void keepdata() {
        try {
            con = Connective.getConnection();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String time = simpleDateFormat.format(date);
            String sql = "insert into " + welcome.getUser() + " values(null,?,?);";
            pst = con.prepareStatement(sql);
            pst.setString(1, time);
            pst.setInt(2, Level.getprevisousLevel());
            pst.executeUpdate();
        } catch (ClassNotFoundException e) {
            System.out.println(e);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
