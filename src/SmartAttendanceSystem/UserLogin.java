package SmartAttendanceSystem;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogin {

    public TextField tb_email;
    public Button btn_login;
    public Button btn_cancel;
    public PasswordField tb_password;

    public void btn_login_click() throws SQLException, IOException {
        String UserEmailAddress = tb_email.getText();
        String PasswordHash = getHash(tb_password.getText());

        User user = new UserDAO().getUser(UserEmailAddress, PasswordHash);

        getMacAddress();

    }

    public String getMacAddress() throws IOException {
        InetAddress ip;
        StringBuilder sb = null;
        try {
            
            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());

        } catch (Exception e) {

            e.printStackTrace();
        }
        getRouterAddress();

        return sb.toString();
    }

    public void getRouterAddress() throws IOException {
        String routerIpAddress=null;
        String command = "ipconfig /all";
        Process p = Runtime.getRuntime().exec(command);

        BufferedReader inn = new BufferedReader(new InputStreamReader(p.getInputStream()));
        Pattern pattern = Pattern.compile(".*DHCP Server.*: (.*)");

        while (true) {
            String line = inn.readLine();

            if (line == null)
                break;

            Matcher mm = pattern.matcher(line);
            if (mm.matches()) {
                routerIpAddress= mm.group(1);
            }

        }
        System.out.println("Router IP Address: " + routerIpAddress);

        getRouterMac(routerIpAddress);

    }

    public void getRouterMac(String ipAddress) throws IOException {
        System.out.println("1-----------------------");
        String commandM = "arp -a";
        Process pM = Runtime.getRuntime().exec(commandM);

        BufferedReader innM = new BufferedReader(new InputStreamReader(pM.getInputStream()));
        Pattern patternM = Pattern.compile(".*"+ipAddress+" (.*)");

        System.out.println("2-----------------------");
        while (true) {
            String lineM = innM.readLine();

            if (lineM == null)
                break;

            Matcher mmM = patternM.matcher(lineM);
            if (mmM.matches()) {
                System.out.println("3-----------------------");
                System.out.println(mmM.group(1).replaceAll("\\s+","").substring(0, 17));
                System.out.println("4-----------------------");
            }
        }
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
