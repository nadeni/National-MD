package Login_in;
public class Open {

    public static void KF( ){
        Keep_file keep_file = new Keep_file();
        keep_file.setLocationRelativeTo(null);
        keep_file.setVisible(true);
    }

    public static void LI() {
        Login_in login_in = new Login_in();
        login_in.setLocationRelativeTo(null);
        login_in.setVisible(true);
        
    }
    public static void LI(String ab) {
        Login_in login_in = new Login_in(ab);
        login_in.setLocationRelativeTo(null);
        login_in.setVisible(true);
    }

    public static void FP() {
        forgetpassword forgetUsername = new forgetpassword();
        forgetUsername.setLocationRelativeTo(null);
        forgetUsername.setVisible(true);
        
    }

    public static void FU() {
        forgetusername forgetUsername = new forgetusername();
        forgetUsername.setLocationRelativeTo(null);
        forgetUsername.setVisible(true);
        
    }

    public static void R() {
        register register = new register();
        register.setLocationRelativeTo(null);
        register.setVisible(true);
        
    }
    public static void W(String ab) {
        welcome register = new welcome(ab);
        register.setLocationRelativeTo(null);
        register.setVisible(true);
        
    }
}
