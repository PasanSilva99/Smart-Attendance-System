package SmartAttendanceSystem;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {

    /**
     * Registers the device on to the database for further use
     * @param macAddress Mac Address of the Device
     * @param deviceName NickName For the device
     */
    DateTimeFormatter Format_TimeSTamp = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public void RegisterDevice(String macAddress, String deviceName, String emailAddress) throws SQLException {
        //SQL Connection
        Connection con =null;
        try{
            // SQL CLass
            Class.forName(DAO.SqlDriverClass);
            // SQL COnnection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            // SQL Statement
            String sql = "INSERT INTO device (mac_address, device_name, owner_email) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE mac_address=?, device_name=?, owner_email=?";

            // Preparing a statemment
            PreparedStatement statement = con.prepareStatement(sql);
            // Setting the parameters (VALUES (?, ?, ?))
            //                                (1, 2, 3)
            statement.setString(1, macAddress); // mac_address
            statement.setString(2, deviceName); // device_name
            statement.setString(3, emailAddress); // owner_email
            statement.setString(4, macAddress); // mac_address
            statement.setString(5, deviceName); // device_name
            statement.setString(6, emailAddress); // owner_email

            // Executing the statement with a variable to get how many records were updated
            int rowsInserted = statement.executeUpdate();

            // Checking whether the inserted values is larger that 0
            // if not, that means there is a problem with the quarry
            if(rowsInserted>0){
                System.out.println("New Device Registered Successfully");
            }else {
                System.out.println("Unknown error occurred in device Registration");
            }


        }catch (Exception e){
            System.out.println("ERROR ! Registering the Device !!!" + e.getMessage());

        }finally {
            // if the connection is not null and the connection is not closed
            if(con!=null&&!con.isClosed()){
                con.close();  // Close the Connection
            }
        }
    }

    /**
     * Removes the device from the database
     * @param macAddress
     */
    public void UnRegisterDevice(String macAddress) throws SQLException {
        // SQL Connection V
        Connection con = null;
        try{
            // SQL Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "DELETE FROM device WHERE mac_address=?";

            //Preparing the statement
            PreparedStatement statement = con.prepareStatement(sql);
            // Setting the statement variables
            statement.setString(1, macAddress);

            // Executing the statement with a variable to collect the return value
            // 0 - No rows updated or null
            // 1+ - Updated row count
            int rowsDeleted = statement.executeUpdate();

            //check the updated rows is larger that 0
            if(rowsDeleted>0){
                System.out.println("Successfully Deleted "+macAddress);
            }else {
                System.out.println("Unknown error occurred while deletion or the provided mac address is wrong.");
            }

        }catch (Exception e){
            System.out.println("ERROR <!> Unregistering Device !!! "+e.getMessage());
        }finally {
            // if the connection is not null and the connection is not closed
            if(con!=null&&!con.isClosed()){
                con.close();  // Close the Connection
            }
        }


    }

    /**
     * Changes the nickname Recorded in the database
     * @param newName
     */
    public void ChangeDeviceName(String newName, String macAddress) throws SQLException {
        // SQL Connection V
        Connection con = null;
        try {
            // SQL Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "UPDATE device SET device_name=? WHERE mac_address=?";
            //Preparing the statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setString(2, macAddress);

            // Executing the statement with a variable to collect the return value
            // 0 - No rows updated or null
            // 1+ - Updated row count
            int rowsDeleted = statement.executeUpdate();

            //check the updated rows is larger that 0
            if(rowsDeleted>0){
                System.out.println("Successfully Deleted "+macAddress);
            }else {
                System.out.println("Unknown error occurred while Updating or the provided MacAddress/ NewName is wrong.");
            }

        }catch (Exception e){
            System.out.println("ERROR <!> Updating Device Name !!! " + e.getMessage());
        }finally {
            // if the connection is not null and the connection is not closed
            if(con!=null&&!con.isClosed()){
                con.close();  // Close the Connection
            }
        }
    }

    /**
     * Records a session on a Unknown Device
     * @param mac_address Mac Address of the logged in device
     * @param ipAddress Current IP Address
     * @param timeStamp TimeStamp on the login instance
     */
    public void RecordLogin(String mac_address, String ipAddress, String userEmailAddress, Timestamp timeStamp) throws SQLException {
        // SQL Connection
        Connection con =null;
        try{
            // SQL Class
            Class.forName(DAO.SqlDriverClass);
            //SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            //SQL Quarry
            String sql = "INSERT INTO device_ip (device_mac_address, device_ip_address, date, logged_email) VALUES(?, ?, ?, ?)";
            // Preparing the statement
            PreparedStatement statement = con.prepareStatement(sql);
            // setting the sql variables
            statement.setString(1, mac_address);
            statement.setString(2, ipAddress);
            statement.setTimestamp(3, timeStamp);
            statement.setString(4, userEmailAddress);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected>0){
                System.out.println("Login successful");
            }

        }catch (Exception e){
            System.out.println("ERROR <!> Recording Attendance !!! " + e.getMessage());
        }finally {
            // if the connection is not null and the connection is not closed
            if(con!=null&&!con.isClosed()){
                con.close();  // Close the Connection
            }
        }
    }

    /**
     * This function returns the Auto logged in user's information
     * @param emailAddress
     * @param deviceMacAddress
     * @return
     */
    public User AutoLogin(String emailAddress, String deviceMacAddress) throws SQLException {
        System.out.println("Auto Login Sequence Start");

        User user = null;
        
        // SQL Connection variable;
        Connection con = null;
        try{

            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            System.out.println("Auto Login Connection Established");
            String sql = "SELECT * FROM device WHERE owner_email=? AND mac_address=?";
            
            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, emailAddress);
            statement.setString(2, deviceMacAddress);
            
            int rowsFound = statement.executeUpdate();
            
            if (rowsFound>0){
                // Valid User
                user = new UserDAO().getUser(emailAddress);
                System.out.println("Auto Login User Identified");
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            UserLogin userLogin = new UserLogin();
            RecordLogin(userLogin.getDeviceMacAddress(), userLogin.getDeviceIPAddress().toString(), emailAddress, timestamp);
            System.out.println("Auto Login Successful");

        }catch (Exception e){
            System.out.println("ERROR <!> Auto Login !!!" + e.getMessage());
            user = null;

        }finally {
            assert con != null;
            con.close();
        }


        return user;
    }

    /**
     * Confirms the ownership of the device.
     * @param emailAddress Users Email address
     * @param macAddress Logged in Mac Address
     * @return Boolean Confirmation
     */
    public boolean checkOwnership(String emailAddress, String macAddress) throws SQLException {
        System.out.println("Ownership Confirmation Start");

        // SQL Connection Variable
        Connection con = null;
        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT COUNT(*) FROM device WHERE mac_address=? AND owner_email=?";

            // SQL Statement
            PreparedStatement statement= con.prepareStatement(sql);
            statement.setString(1, macAddress);
            statement.setString(2, emailAddress);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                {
                    int count = resultSet.getInt(1);
                    if(count > 0 )
                    {
                        System.out.println("OwnerShip Confirmed");
                        return true;
                    }
                }
            }

        }catch (Exception e){
            System.out.println("ERROR <!> Ownership Confirmation Error !!!" + e.getMessage());
        }finally {
            con.close();
        }
        System.out.println(macAddress + " is not owned by " + emailAddress);
        return false;
    }
}

