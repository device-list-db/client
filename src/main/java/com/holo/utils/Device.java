package com.holo.utils;

import org.springframework.util.StringUtils;

/**
 * Device object code
 * @since 0.1.0
 * @version 0.2.0
 */
public class Device {
    /**
     * The length of all Media Access Control addressees
     */
    public static final int MAC_ADDRESS_LENGTH = 17;

    private String serialNumber;
    private char[] macAddress;
    private String deviceName;
    private String owner;

    /**
     * Create a blank device object
     */
    public Device() {
        macAddress = new char[Device.MAC_ADDRESS_LENGTH];
    }

    /**
     * Register a device in memory, if it has not been setup already
     * @param serialNumber of the device
     * @param macAddress of the device
     * @param deviceName of the device
     * @param owner of the device, as a username
     */
    public void registerDevice(String serialNumber, char[] macAddress, String deviceName, String owner) {
        if (!StringUtils.isEmpty(this.serialNumber)) // Do not allow to be used after device is set up
            return;
        this.serialNumber = serialNumber;
        this.macAddress = macAddress;
        this.deviceName = deviceName;
        this.owner = owner;
    }

    public String getSerialNumber() { return serialNumber; }

    public String getMacAddress() {
        try {
            return new String(macAddress);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getDeviceName() { return deviceName; }

    public String getOwner() { return owner; }

    /**
     * Should not be used, used only for displaying the table correctly.
     * @return number of fields in a device
     */
    public static int getFieldCount() { return 4; }

    @Override
    public String toString() {
        return deviceName;
    }
}
