package SmartAttendanceSystem;

import com.sun.glass.ui.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModulesDAO {

    public static List<ViewItem> ModuleList;
    public static boolean isConnected = false;

    public List<ViewItem> getModules(){
        if(ModuleList!=null){
            return ModuleList;
        }else{
            fetchModules();
            return ModuleList;
        }
    }

    public static void fetchModules(){
        ModuleList = new ArrayList<ViewItem>();

        Connection con = null;
        try{
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sas_db", "root", "root");

            System.out.println("Connection to SAS_DB Succeeded.");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from module");
            while(rs.next()) {

                //Fetch Details
                String ModuleCode = rs.getString(1);
                String ModuleName = rs.getString(2);
                String ImagePath = rs.getString(3);

                System.out.println("Fetching : " + ModuleCode + "  " + ModuleName + "  " + ImagePath);

                if (ImagePath == null)
                    ImagePath = "Images/modules.png";

                ModuleList.add(new ViewItem(ModuleCode, ModuleName, "", ImagePath));
            }
            con.close();

            }catch(SQLException e){
                System.out.println(e);
                }

    isConnected = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SEVER ERROR!!! Connection to SAS_DB Failed!\n"+e);
            isConnected = false;
        }

    }

    public static boolean checkModules(){
        fetchModules();
        return isConnected;

    }
}
