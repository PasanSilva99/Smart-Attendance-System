package SmartAttendanceSystem;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserLogin {

    public TextField tb_email;
    public Button btn_login;
    public Button btn_cancel;
    public PasswordField tb_password;

    public void btn_login_click() throws SQLException {
        String UserEmailAddress = tb_email.getText();
        String PasswordHash = getHash(tb_password.getText());

        User user = new UserDAO().getUser(UserEmailAddress, PasswordHash);



    }

    public String getHash(String passwordToHash){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);

        return generatedPassword;

    }

    public void closeApp(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void btnClose_Click(){
        closeApp(btn_cancel);
    }
}
