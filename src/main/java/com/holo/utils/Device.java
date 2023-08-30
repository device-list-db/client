package com.holo.utils;

import org.springframework.util.StringUtils;

public class Device {
    public static final int MAC_ADDRESS_LENGTH = 17;

    private String serialNumber;
    private char[] macAddress;
    private String deviceName;
    private String owner;

    public Device() {
        macAddress = new char[Device.MAC_ADDRESS_LENGTH];
    }

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

    public static int getFieldCount() { return 4; }

    @Override
    public String toString() {
        return deviceName;
    }
}
