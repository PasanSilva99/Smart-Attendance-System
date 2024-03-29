package Common;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO {
    public List<User> UsersList;
    public boolean isConnected = false;

    public List<User> getUsers() throws SQLException {
        if (UsersList == null) {
            fetchUsers();
        }
        return UsersList;
    }

    public void fetchUsers() throws SQLException {
        UsersList = new ArrayList<>();

        Connection con = null;

        try{

            Class.forName(DAO.SqlDriverClass);

            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            System.out.println("Connection to SAS_DB Succeeded.");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM user");
            while(rs.next()) {

                //Fetch Details
                String NSBM_ID = rs.getString(1);
                String Prefix = rs.getString(2);
                String Name = rs.getString(3);
                String Email = rs.getString(4);
                String PasswordHash = rs.getString(5);
                String DegreeProgram = rs.getString(6);
                String Batch = rs.getString(7);
                String PrivilegeLevel = rs.getString(8);

                if(!(NSBM_ID==null)) {
                    System.out.println("Fetching Users : " + NSBM_ID + "  " + Prefix + "  " + Name + "  " + Email + "  " + DegreeProgram + "  " + Batch + "  " + PrivilegeLevel);


                    UsersList.add(new User(NSBM_ID, Prefix, Name, Email, PasswordHash, DegreeProgram, Batch, PrivilegeLevel));
                }
            }
            isConnected = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SEVER ERROR!!! Connection to SAS_DB Failed!\n"+e);
            isConnected = false;
        }  finally {
            con.close();
        }

    }

    public boolean CheckUsers() throws SQLException {
        fetchUsers();
        return isConnected;
    }

    public boolean CheckUser(String email, String passwordHash) throws SQLException {
        // SQL Connection Variable
        Connection con = null;
        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

           // SQL Quarry
            String sql = "SELECT COUNT(*) FROM user WHERE nsbm_email=? AND password_hash=?";
            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, passwordHash);

            try (ResultSet resultSet = statement.executeQuery()) {
              while (resultSet.next())
              {
                  int count = resultSet.getInt(1);
                  if(count>0)
                  {
                      System.out.println("User Valid");
                      return true;
                  }
              }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            assert con != null;
            con.close();
        }
        return false;
    }

    public void AddUser(User user) throws SQLException {
        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "INSERT INTO user (nsbm_id, prefix, name, nsbm_email, password_hash, degree_program, batch, privilege_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getNsbm_id());
            statement.setString(2, user.getPrefix());
            statement.setString(3, user.getName());
            statement.setString(4, user.getNsbm_email());
            statement.setString(5, user.getPassword_hash());
            statement.setString(6, user.getDegree_program());
            statement.setString(7, user.getBatch());
            statement.setString(8, user.getPriviladgeLevel());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user added successfully!");
            }
        }catch (Exception e){
            System.out.println("User Record Failed.");
        }finally {
            assert con != null;
            if(!con.isClosed())
            {
                con.close();
            }
        }
    }

    public void RemoveUser(String nsbm_id) throws SQLException {
        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "DELETE FROM user WHERE nsbm_id="+nsbm_id;

            PreparedStatement statement = con.prepareStatement(sql);
            statement.execute();
            System.out.println("User Deleted Successfully");
        }catch (Exception e){
            System.out.println("User Deletion Failed");
        }finally {
            if(!con.isClosed())
            {
                con.close();
            }
        }
    }

    public void UpdateUser(User user) throws SQLException {
        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "UPDATE user SET first_name=?, last_name=?, nsbm_email=?, password_hash=?, degree_program=?, batch=?, privilege_level=? WHERE nsbm_id=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getPrefix());
            statement.setString(2, user.getName());
            statement.setString(3, user.getNsbm_email());
            statement.setString(4, user.getPassword_hash());
            statement.setString(5, user.getDegree_program());
            statement.setString(6, user.getBatch());
            statement.setString(7, user.getPriviladgeLevel());
            statement.setString(8, user.getNsbm_id());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User Updated successfully!");
            }
        }catch (Exception e){
            System.out.println("User Record Failed.");
        }finally {
            assert con != null;
            if(!con.isClosed())
            {
                con.close();
            }
        }
    }

    public void getModules(User user){
        
    }

    public boolean checkLoggedUser() throws FileNotFoundException, SQLException {

        File file = new File("User.bin");
        if(file.exists())
        {
            Scanner scan = new Scanner(file);
            if (scan.hasNext()){
                return true;
            }
        }

        return false;
    }

    public String GetPrivilege(String UserId) throws SQLException {
        String privilege_level  = null;
        // Sql connection
        Connection con = null;
        try{

            Class.forName(DAO.SqlDriverClass);
            con= DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            String sql = "SELECT privilege_level FROM user WHERE nsbm_id=?";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
              preparedStatement.setString(1, UserId);

              try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    privilege_level = resultSet.getString(1);
                }
              }
            }


        }catch (Exception e){
            return null;
        }finally {
            con.close();
        }

        return privilege_level;
    }

    public User getUser(String email, String password) throws SQLException {
        User user = null;
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM user WHERE nsbm_email=? AND password_hash=?";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
              while (resultSet.next()){
                  String nsbm_id = resultSet.getString(1);
                  String prefix = resultSet.getString(2);
                  String name = resultSet.getString(3);
                  String nsbm_emails = resultSet.getString(4);
                  String password_hash = resultSet.getString(5);
                  String degree_program = resultSet.getString(6);
                  String batch = resultSet.getString(7);
                  String privilege_level = resultSet.getString(8);

                  user = new User(nsbm_id, prefix, name, nsbm_emails, password_hash, degree_program, batch, privilege_level);
              }
            }

        }catch (Exception e){

        }finally {
            assert con != null;
            con.close();
        }

        return user;
    }

    public User getUser(String emailAddress) throws SQLException {
        User user = null;
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM user WHERE nsbm_email=?";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, emailAddress);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    String nsbm_id = resultSet.getString(1);
                    String prefix = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    String nsbm_emails = resultSet.getString(4);
                    String password_hash = resultSet.getString(5);
                    String degree_program = resultSet.getString(6);
                    String batch = resultSet.getString(7);
                    String privilege_level = resultSet.getString(8);

                    user = new User(nsbm_id, prefix, name, nsbm_emails, password_hash, degree_program, batch, privilege_level);
                }
            }

        }catch (Exception e){

        }finally {
            assert con != null;
            con.close();
        }

        return user;

    }

    public User getStudent(String nsbmID) throws SQLException {
        User user = null;
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM user WHERE nsbm_id=?";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nsbmID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    String nsbm_id = resultSet.getString(1);
                    String prefix = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    String nsbm_emails = resultSet.getString(4);
                    String password_hash = resultSet.getString(5);
                    String degree_program = resultSet.getString(6);
                    String batch = resultSet.getString(7);
                    String privilege_level = resultSet.getString(8);

                    user = new User(nsbm_id, prefix, name, nsbm_emails, password_hash, degree_program, batch, privilege_level);
                }
            }

        }catch (Exception e){

        }finally {
            assert con != null;
            con.close();
        }

        return user;

    }

    public List<User> getStudentList() {
        List<User> StudentList = new ArrayList<>();

        // SQL Connection con
        Connection con=null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            // SQL Quarry
            String sql = "SELECT * FROM user WHERE privilege_level='student'";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String nsbm_id = resultSet.getString(1);
                String prefix = resultSet.getString(2);
                String name = resultSet.getString(3);
                String nsbm_email = resultSet.getString(4);
                String password_hash = resultSet.getString(5);
                String degree_program = resultSet.getString(6);
                String batch = resultSet.getString(7);
                String privilege_level = resultSet.getString(8);

                StudentList.add( new User(nsbm_id, prefix, name, nsbm_email, password_hash, degree_program, batch, privilege_level));
                System.out.println("Fetching: Student "+nsbm_id+" " +prefix+" "+name);

            }


        }catch (Exception e){

        }finally {
            try {
                con.close();
            }catch (Exception ignored){}
        }
        return StudentList;
    }

    public List<User> getLecturerList() {
        List<User> LecturerList = new ArrayList<>();

        // SQL Connection con
        Connection con=null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            // SQL Quarry
            String sql = "SELECT * FROM user WHERE privilege_level='lecturer'";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String nsbm_id = resultSet.getString(1);
                String prefix = resultSet.getString(2);
                String name = resultSet.getString(3);
                String nsbm_email = resultSet.getString(4);
                String password_hash = resultSet.getString(5);
                String degree_program = resultSet.getString(6);
                String batch = resultSet.getString(7);
                String privilege_level = resultSet.getString(8);

                LecturerList.add( new User(nsbm_id, prefix, name, nsbm_email, password_hash, degree_program, batch, privilege_level));
                System.out.println("Fetching: Lecturer "+nsbm_id+" " +prefix+" "+name);

            }


        }catch (Exception e){

        }finally {
            try {
                con.close();
            }catch (Exception ignored){}
        }
        return LecturerList;
    }

    public void markAttendance(String nsbm_id, String deviceMacAddress, double marks, String routerMacAddress, String moduleCode, String event_id, String quiz_id) {

        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "INSERT INTO attendance VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nsbm_id);
            statement.setString(2, deviceMacAddress);
            statement.setDouble(3, marks);
            statement.setString(4, routerMacAddress);
            statement.setString(5, moduleCode);
            statement.setString(6, event_id);
            statement.setString(7, quiz_id);
            statement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Attendance record added successfully!");
                Alert.AlertType alertAlertType = AlertType.CONFIRMATION;
                Alert alert = new Alert(alertAlertType);
                alert.setContentText("Attendance Marked Successfully");
                alert.setHeaderText("Attendance Marked");
                alert.show();
            }
        }catch (Exception e){
            System.out.println("Attendance record Failed");
            Alert.AlertType alertAlertType = AlertType.ERROR;
            Alert alert = new Alert(alertAlertType);
            alert.setContentText("Attendance Marking Error");
            alert.setHeaderText("Error");
            alert.show();
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception ignored){}
        }

    }

    public void removeUser(String id){
        Connection con = null;

        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "DELETE FROM user WHERE nsbm_id=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student Removed successfully!");
                Alert.AlertType alertAlertType = AlertType.CONFIRMATION;
                Alert alert = new Alert(alertAlertType);
                alert.setContentText("Student Removed Successfully");
                alert.setHeaderText("Student Removed");
                alert.show();
            }
        }catch (Exception e){
            System.out.println("Removing Student Failed");
            Alert.AlertType alertAlertType = AlertType.ERROR;
            Alert alert = new Alert(alertAlertType);
            alert.setContentText("Removing Student Error");
            alert.setHeaderText("Error");
            alert.show();
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception ignored){}
        }
    }
}
