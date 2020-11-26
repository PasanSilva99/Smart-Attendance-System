package SmartAttendanceSystem;

import java.sql.*;
import java.util.List;

public class Device {
    public String mac_address;
    public String DeviceName;
    public String DeviceType;
    public List<String> IPaddressList;

    /* Getters and Setters  */
    public String getMac_address() {
        return mac_address;
    }
    /* Getters and Setters  */
    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }
    /* Getters and Setters  */
    public String getDeviceName() {
        return DeviceName;
    }
    /* Getters and Setters  */
    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }
    /* Getters and Setters  */
    public String getDeviceType() {
        return DeviceType;
    }
    /* Getters and Setters  */
    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    /**
     * This method updated the IP address list according to the device mac address
     */
    public List<String> UpdateIPList() throws SQLException {
        //SQL Connection
        Connection con = null;
        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            //SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            //SQL Quarry
            String sql = "SELECT device_ip_address FROM device_ip WHERE device_mac_address=?";

            //Preparing the statement
            PreparedStatement statement = con.prepareStatement(sql);
            // Setting the variables in the statement
            statement.setString(1, mac_address);

            // Get the reading values
            ResultSet readData = statement.executeQuery();
            while (readData.next()){

                String IpAddress = readData.getString(1);
                if(IpAddress != null) {
                    IPaddressList.add(IpAddress);
                    System.out.println("Feting IP Address for " + mac_address + ": " + IpAddress);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR <!> Fetching IP Addresses for "+mac_address+" !!! "+e);
        }  finally {
            assert con != null;
            con.close();
        }


        return IPaddressList;
    }

}
