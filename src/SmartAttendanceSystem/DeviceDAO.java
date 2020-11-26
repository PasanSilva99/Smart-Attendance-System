package SmartAttendanceSystem;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeviceDAO {

    /**
     * Registers the device on to the database for further use
     * @param macAddress Mac Address of the Device
     * @param deviceName NickName For the device
     * @param deviceType The type of the device (1-Laptop, 2-MobilePhone, 3-TabletPC, 4-Tablet, 5-Other)
     */
    DateTimeFormatter Format_TimeSTamp = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public void RegisterDevice(String macAddress, String deviceName, int deviceType) throws SQLException {
        //SQL Connection
        Connection con =null;
        try{
            // SQL CLass
            Class.forName(DAO.SqlDriverClass);
            // SQL COnnection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            // SQL Statement
            String sql = "INSERT INTO device (mac_address, device_name, device_type) VALUES (?, ?, ?)";

            // Preparing a statemment
            PreparedStatement statement = con.prepareStatement(sql);
            // Setting the parameters (VALUES (?, ?, ?))
            //                                (1, 2, 3)
            statement.setString(1, macAddress); // mac_address
            statement.setString(2, deviceName); // device_name
            statement.setInt(3, deviceType); // device_type

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
            System.out.println("ERROR ! Registering teh Device !!!");

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
    public void RecordLogin(String mac_address, String ipAddress, String timeStamp) throws SQLException {
        // SQL Connection
        Connection con =null;
        try{
            // SQL Class
            Class.forName(DAO.SqlDriverClass);
            //SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            //SQL Quarry
            String sql = "INSERT INTO device_ip (device_mac_address, device_ip_address, date) VALUES(?, ?, ?)";
            // Preparing the statement
            PreparedStatement statement = con.prepareStatement(sql);
            // setting the sql variables
            statement.setString(1, mac_address);
            statement.setString(2, ipAddress);
            statement.setString(3, timeStamp);

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
     * Records a known device login
     * @param device Device
     * @param ipAddress Current IP address
     */
    public void RecordLogin(Device device, String ipAddress) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        RecordLogin(device.mac_address, ipAddress, Format_TimeSTamp.format(now));
        device.UpdateIPList();
    }

    /**
     * Fetch all device types from the database
     */
    private void GetDeviceTypes(){}

    /**
     * Add a new device type to the database
     */
    private void AddDeviceTypes(){}

    /**
     * Search Device NickName By Mac Address
     * @param MacAddress Mac Address of the logged in device
     *
     * @return Device
     */
    public Device GetDeviceNamebyMac(String MacAddress){return null;}

    /** 
     * Search Device Mac Address by NickName
     * @param DeviceName Name of the Device
     * @return Device
     */
    public Device GetDeviceMacByName(String DeviceName){return null;}
}

