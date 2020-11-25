package SmartAttendanceSystem;

public class DeviceDAO {

    /**
     * Registers the device on to the database for further use
     * @param macAddress Mac Address of the Device
     * @param deviceName NickName For the device
     * @param deviceType The type of the device (1-Laptop, 2-MobilePhone, 3-TabletPC, 4-Tablet, 5-Other)
     */
    public void RegisterDevice(String macAddress, String deviceName, String deviceType){

    }

    /**
     * Removes the device from the database
     * @param macAddress
     */
    public void UnRegisterDevice(String macAddress){}

    /**
     * Changes the nickname Recorded in the database
     * @param newName
     */
    public void ChangeDeviceName(String newName){}

    /**
     * Records a session on the Device
     * @param mac_address Mac Address of the logged in divice
     * @param deviceName Nickname Of the device
     * @param ipAddress Current IP Address
     */
    public void RecordLogin(String mac_address, String deviceName, String ipAddress){}

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

