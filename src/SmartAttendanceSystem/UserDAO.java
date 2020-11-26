package SmartAttendanceSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

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
                String FirstName = rs.getString(2);
                String LastName = rs.getString(3);
                String Email = rs.getString(4);
                String PasswordHash = rs.getString(5);
                String DegreeProgram = rs.getString(6);
                String Batch = rs.getString(7);
                String PrivilegeLevel = rs.getString(8);

                if(!(NSBM_ID==null)) {
                    System.out.println("Fetching Users : " + NSBM_ID + "  " + FirstName + "  " + LastName + "  " + Email + "  " + DegreeProgram + "  " + Batch + "  " + PrivilegeLevel);


                    UsersList.add(new User(NSBM_ID, FirstName, LastName, Email, PasswordHash, DegreeProgram, Batch, PrivilegeLevel));
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

    public void AddUser(User user) throws SQLException {
        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
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

    public void getModules(User user){
        
    }

    public boolean checkLoggedUser(){

        return true;
    }
}
