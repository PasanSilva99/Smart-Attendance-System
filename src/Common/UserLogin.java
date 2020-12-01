package Common;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
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

        String DeviceMac = getDeviceMacAddress();
        String RouterMac = getRouterMacAddress();

        if(new UserDAO().CheckUser(UserEmailAddress, PasswordHash)){

            System.out.println("User Identified");

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String ipAddress = getDeviceIPAddress().toString();
            String macAddress = getDeviceMacAddress();
            int dev = ipAddress.indexOf("/");

            String deviceName = "UnNamed";
            if (dev != -1)
            {
                deviceName= ipAddress.substring(0 , dev);
            }

            new DeviceDAO().RecordLogin(DeviceMac, getDeviceIPAddress().toString(), UserEmailAddress, timestamp);
            new DeviceDAO().RegisterDevice(getDeviceMacAddress(), deviceName, UserEmailAddress);

            File file = new File("User.bin");
            FileWriter writer = new FileWriter(file);
            writer.write(UserEmailAddress);
            writer.close();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("SplashScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
            Stage stage = new Stage();
            stage.setTitle(" ");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

            closeApp(btn_login);
        }
        else {
            Alert.AlertType alertAlertType = AlertType.ERROR;
            Alert alert= new Alert(alertAlertType);
            alert.setTitle("Login Error");
            alert.setContentText("Please make user you entered the email and password correctly");
            alert.setHeaderText("Cannot find user");
            alert.show();
        }

    }

    public void btn_cancel_click(){
        exitApp();
    }
    public InetAddress getDeviceIPAddress() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println("Current IP address : " + ip.getHostAddress());

        return ip;

    }
    public String getDeviceMacAddress() throws IOException {
        InetAddress ip = getDeviceIPAddress();
        StringBuilder sb = null;
        try {
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
        getRouterIPAddress();

        return sb.toString();
    }
    public String getRouterIPAddress() throws IOException {
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

        return routerIpAddress;

    }
    public String getRouterMacAddress() throws IOException {
        String ipAddress = getRouterIPAddress();
        String routerMacAddress = null;
        String commandM = "arp -a";
        Process pM = Runtime.getRuntime().exec(commandM);

        BufferedReader innM = new BufferedReader(new InputStreamReader(pM.getInputStream()));
        Pattern patternM = Pattern.compile(".*"+ipAddress+" (.*)");

        while (true) {
            String lineM = innM.readLine();

            if (lineM == null)
                break;

            Matcher mmM = patternM.matcher(lineM);
            if (mmM.matches()) {
                routerMacAddress = mmM.group(1).replaceAll("\\s+","").substring(0, 17);
                System.out.println("Routuer Mac Address : " + routerMacAddress);
            }
        }
        return routerMacAddress;
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
    public void exitApp(){
        Platform.exit();
        System.exit(0);
    }

    public void markAttendance(User user, String module) throws IOException {
        new UserDAO().markAttendance(user.getNsbm_id(), getDeviceMacAddress(), 100, getRouterMacAddress(), module);
    }

}
