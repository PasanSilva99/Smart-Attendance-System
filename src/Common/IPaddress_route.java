package Common;

public class IPaddress_route {
    public String Device_mac_address;
    public String Device_ip_address;
    public String TimeStamp;

    /**
     * Records an unknown device
     * @param device_mac_address Mac Address of the device
     * @param device_ip_address IP Address
     * @param timeStamp TimeStamp
     */
    public IPaddress_route(String device_mac_address, String device_ip_address, String timeStamp){
        Device_mac_address = device_mac_address;
        Device_ip_address = device_ip_address;
        TimeStamp = timeStamp;
    }

    /**
     * Saves a login to the database from a known Device
     * @param device The Device
     * @param ipAddress Current IP Address
     * @param timeStamp TimeStamp
     */
    public void SaveLogin(Device device, String ipAddress, String timeStamp){
        Device_mac_address = device.getMac_address();
        Device_ip_address = ipAddress;
        TimeStamp = timeStamp;


    }
}
