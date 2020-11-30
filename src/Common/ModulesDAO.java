package Common;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModulesDAO {

    public static List<ViewItem> ModuleViewItemList;
    public static List<Module> ModuleList;
    public static boolean isConnected = false;

    public static ObservableList<String> getModuleCodesOL() {
        List<String> listModule = FXCollections.observableArrayList();

        fetchModules();

        for (ViewItem item: ModuleViewItemList) {
            if(item!=null)
                listModule.add(item.ModuleCode);
        }

        return FXCollections.observableArrayList(listModule);
    }

    public static String getLecturerName(String moduleCode) {

        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT lecturer_name FROM module WHERE module_code=?";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, moduleCode);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                return resultSet.getString(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<ViewItem> getModuleViewItemList() throws SQLException {
        if (ModuleViewItemList == null) {
            fetchModules();
        }
        return ModuleViewItemList;
    }

    public List<Module> getModuleList(){
        if (ModuleList == null) {
            fetchModules();
        }
        return ModuleList;
    }

    public static void fetchModules(){
        ModuleViewItemList = new ArrayList<>();

        Connection con = null;

        try{

            Class.forName(DAO.SqlDriverClass);

            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            System.out.println("Connection to SAS_DB Succeeded.");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from module");
            while(rs.next()) {

                //Fetch Details
                String ModuleCode = rs.getString(1);
                String ModuleName = rs.getString(2);
                String LecturerName = rs.getString(3);
                String DegreeProgram = rs.getString(4);

                System.out.println("Fetching : " + ModuleCode + "  " + ModuleName + "  " + LecturerName + "  " + DegreeProgram);

                ModuleViewItemList.add(new ViewItem(ModuleCode, ModuleName, "", "Images/modules.png"));


            }
            isConnected = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SEVER ERROR!!! Connection to SAS_DB Failed!\n"+e);
            isConnected = false;
        }  finally {
            try {
                assert con != null;
                con.close();
            }catch (Exception ignored){
                
            }
        }

    }

    public static boolean checkModules() throws SQLException {
        fetchModules();
        return isConnected;
    }

    public void addModule(Module module) throws SQLException {
        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "INSERT INTO module (module_code, module_name, lecturer_name, degree_program) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, module.getModuleCode());
            statement.setString(2, module.getModuleName());
            statement.setString(3, module.getLecturerName());
            statement.setString(4, module.getDegreeProgram());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new module was inserted successfully!");
            }
        }catch (Exception e){
            System.out.println("New module insertion Error");
        }finally {
            assert con != null;
            if(!con.isClosed())
            {
                con.close();

            }
        }
    }

    public void RemoveModule(String moduleCode) throws SQLException {
        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "DELETE FROM module WHERE module_code="+moduleCode;

            PreparedStatement statement = con.prepareStatement(sql);
            statement.execute();
            System.out.println("Successfully Removed the module");
        }catch (Exception e){
            System.out.println("Error removing module");
        }finally {
            assert con != null;
            if(!con.isClosed())
            {
                con.close();
            }
        }
    }

    public void UpdateModule(String moduleCode, Module module) throws SQLException {
        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "UPDATE module SET module_name=?, lecturer_name=?, degree_program=?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, module.ModuleName);
            statement.setString(2, module.LecturerName);
            statement.setString(3, module.DegreeProgram);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Module was Updated successfully!");
            }
        }catch (Exception e){
            System.out.println("Failed to Update the module");
        }finally {
            assert con != null;  // con may produce a nullEx
            if(!con.isClosed())  // if the connection is not closed
            {
                con.close();     // close the connection
            }
        }
    }


    public List<Module> getModulesList() {
        List<Module> ModulesList = new ArrayList<>();

        // SQL Connection con
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            // SQL Quarry
            String sql = "SELECT * FROM module ";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String module_code = resultSet.getString(1);
                String module_name = resultSet.getString(2);
                String lecturer_name = resultSet.getString(3);
                String degree_program = resultSet.getString(4);



                ModulesList.add( new Module(module_code,module_name,lecturer_name,degree_program));
                System.out.println("Fetching: Module  "+module_code+" " +module_name+" ");

            }


        }catch (Exception e){

        }finally {
            try {
                con.close();
            }catch (Exception ignored){}
        }
        return ModulesList;
    }




    public Module getModuleByID(String moduleO) {
        Connection con = null;

        try{

            Class.forName(DAO.SqlDriverClass);

            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            System.out.println("Connection to SAS_DB Succeeded.");

            String sql = "SELECT * FROM module WHERE module_code=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, moduleO);
            ResultSet resultSet=statement.executeQuery();

            while(resultSet.next()) {

                //Fetch Details
                String ModuleCode = resultSet.getString(1);
                String ModuleName = resultSet.getString(2);
                String LecturerName = resultSet.getString(3);
                String DegreeProgram = resultSet.getString(4);

                System.out.println("Fetching : " + ModuleCode + "  " + ModuleName + "  " + LecturerName + "   " + DegreeProgram);

                return new Module(ModuleCode, ModuleName, LecturerName, DegreeProgram);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR <!> Cannot fetch Modules\n"+e);
        }  finally {
            try {
                assert con != null;
                con.close();
            }catch (Exception ignored){

            }
        }
        return null;
    }

}

