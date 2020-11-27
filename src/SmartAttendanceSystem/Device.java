package SmartAttendanceSystem;

import java.sql.*;
import java.util.List;

public class Device {
    public String Mac_address;
    public String DeviceName;
    public List<String> IPaddressList;

    public Device(String macAddress, String deviceName) {
        Mac_address = macAddress;
        DeviceName = deviceName;
    }

    /* Getters and Setters  */
    public String getMac_address() {
        return Mac_address;
    }
    /* Getters and Setters  */
    public void setMac_address(String mac_address) {
        this.Mac_address = mac_address;
    }
    /* Getters and Setters  */
    public String getDeviceName() {
        return DeviceName;
    }
    /* Getters and Setters  */
    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
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
            statement.setString(1, Mac_address);

            // Get the reading values
            ResultSet readData = statement.executeQuery();
            while (readData.next()){

                String IpAddress = readData.getString(1);
                if(IpAddress != null) {
                    IPaddressList.add(IpAddress);
                    System.out.println("Feting IP Address for " + Mac_address + ": " + IpAddress);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR <!> Fetching IP Addresses for "+ Mac_address +" !!! "+e);
        }  finally {
            assert con != null;
            con.close();
        }


        return IPaddressList;
    }

}
