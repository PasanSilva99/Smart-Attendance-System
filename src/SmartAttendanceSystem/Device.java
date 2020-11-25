package SmartAttendanceSystem;

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


}
