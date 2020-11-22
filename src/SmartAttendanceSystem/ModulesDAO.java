package SmartAttendanceSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModulesDAO {

    public static List<ViewItem> ModuleList;
    public static boolean isConnected = false;

    public static List<ViewItem> getModules(){
        ModuleList = new ArrayList<ViewItem>();

        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sas_db", "root", "root");

            System.out.println("Connection to SAS_DB Succeeded.");
            isConnected = true;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("SEVER ERROR!!! Connection to SAS_DB Failed!\n"+e);
            isConnected = false;
        }

        return ModuleList;
    }

    public static boolean checkModules(){
        getModules();
        return isConnected;

    }
}
