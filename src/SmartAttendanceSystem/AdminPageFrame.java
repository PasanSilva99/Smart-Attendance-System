package SmartAttendanceSystem;

import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminPageFrame implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File file = new File("User.bin");
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String uname = scan.next();
        System.out.println(uname);


        String filename = "User.bin";
        try{
            PrintWriter outputStream = new PrintWriter(filename);
            outputStream.println("sgetText()");
            outputStream.close();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
