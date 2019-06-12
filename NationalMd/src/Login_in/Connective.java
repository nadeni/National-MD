package Login_in;

import java.sql.DriverManager;
import java.sql.SQLException;




public class Connective {
    public  static java.sql.Connection getConnection() throws SQLException, ClassNotFoundException{
        String driver ="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/Game";
        String password="1234";
        String user="root";
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
//        System.out.println("完成");
        
    }
}
