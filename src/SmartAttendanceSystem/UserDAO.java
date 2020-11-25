package SmartAttendanceSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static List<User> UsersList;
    public static boolean isConnected = false;

    public static String SqlDriverClass = DAO.SqlDriverClass;
    public static String DatabaseUrl = DAO.DatabaseUrl;
    public static String DBuser = DAO.DBuser;
    public static String DBpass = DAO.DBpass;
    public List<User> getUsers() throws SQLException {
        if (UsersList == null) {
            fetchUsers();
        }
        return UsersList;
    }

    public static void fetchUsers() throws SQLException {
        UsersList = new ArrayList<>();

        Connection con = null;

        try{

            Class.forName(SqlDriverClass);  // get the driver class
            con = DriverManager.getConnection(DatabaseUrl, DBuser, DBpass);  // Connect to the database

            System.out.println("Connection to SAS_DB Succeeded.");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM user");
            while(rs.next()) {

                //Fetch Details
                String NSBM_ID = rs.getString(1);
                String FirstName = rs.getString(2);
                String LastName = rs.getString(3);
                String Email = rs.getString(4);
                String PasswordHash = rs.getString(5);
                String DegreeProgram = rs.getString(6);
                String Batch = rs.getString(7);
                String PrivilegeLevel = rs.getString(8);

                System.out.println("Fetching Users : " + NSBM_ID + "  " + FirstName + "  " + LastName + "  " + Email + "  " + DegreeProgram + "  " + Batch + "  " + PrivilegeLevel);


                UsersList.add(new User(NSBM_ID, FirstName, LastName, Email, PasswordHash, DegreeProgram, Batch, PrivilegeLevel));

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

    public static boolean CheckUsers() throws SQLException {
        fetchUsers();
        return isConnected;
    }

    public static void AddUser(User user) throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sas_db", "root", "root");
            String sql = "INSERT INTO user (nsbm_id, first_name, last_name, nsbm_email, password_hash, degree_program, batch, privilege_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getNsbm_id());
            statement.setString(2, user.getFirst_name());
            statement.setString(3, user.getLast_name());
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

    public static void RemoveUser(String nsbm_id) throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sas_db", "root", "root");
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

    public static void UpdateUser(User user) throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sas_db", "root", "root");
            String sql = "UPDATE user SET first_name=?, last_name=?, nsbm_email=?, password_hash=?, degree_program=?, batch=?, privilege_level=? WHERE nsbm_id=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getFirst_name());
            statement.setString(2, user.getLast_name());
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

    public static void getModules(User user){
        Connection con = null;
        try{
            Class.forName(SqlDriverClass);
            con = DriverManager.getConnection(DatabaseUrl, DBuser, DBpass);  // Connect to the database

            System.out.println("Connection to SAS_DB Succeeded.");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM user");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
