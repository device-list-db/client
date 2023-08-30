package com.holo;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.holo.utils.Device;


public class DeviceTests  {
    @Test
    public void DeviceSerialTestTrue() {
        Device d = new Device();
        d.registerDevice("ABCDEFGHIJKLMNOPQ", null, null, null);
        assertTrue(d.getSerialNumber().equals("ABCDEFGHIJKLMNOPQ"));
    }

    @Test
    public void DeviceSerialTestFalse() {
        Device d = new Device();
        d.registerDevice("ABCDEFGHIJKLMNOPQ", null, null, null);
        assertFalse(d.getSerialNumber().equals("abw4e5tdfg"));
    }

    @Test
    public void DeviceSerialTestNullOne() {
        Device d = new Device();
        d.registerDevice("ABCDEFGHIJKLMNOPQ", null, null, null);
        assertNull(d.getMacAddress());
    }

    @Test
    public void DeviceSerialTestNullTwo() {
        Device d = new Device();
        d.registerDevice("ABCDEFGHIJKLMNOPQ", null, null, null);
        assertNull(d.getDeviceName());
    }

    @Test
    public void DeviceSerialTestNullThree() {
        Device d = new Device();
        d.registerDevice("ABCDEFGHIJKLMNOPQ", null, null, null);
        assertNull(d.getOwner());
    }

    @Test
    public void DeviceMacTestTrue() {
        Device d = new Device();
        d.registerDevice(null, "ABCDEFGHIJKLMNOPQ".toCharArray(), null, null);
        assertTrue(d.getMacAddress().equals("ABCDEFGHIJKLMNOPQ"));
    }

    @Test
    public void DeviceMacTestFalse() {
        Device d = new Device();
        d.registerDevice(null, "ABCDEFGHIJKLMNOPQ".toCharArray(), null, null);
        assertFalse(d.getMacAddress().equals("drga45tewr"));
    }

    @Test
    public void DeviceMacTestNullOne() {
        Device d = new Device();
        d.registerDevice(null, "ABCDEFGHIJKLMNOPQ".toCharArray(), null, null);
        assertNull(d.getSerialNumber());
    }

    @Test
    public void DeviceMacTestNullTwo() {
        Device d = new Device();
        d.registerDevice(null, "ABCDEFGHIJKLMNOPQ".toCharArray(), null, null);
        assertNull(d.getDeviceName());
    }

    @Test
    public void DeviceMacTestNullThree() {
        Device d = new Device();
        d.registerDevice(null, "ABCDEFGHIJKLMNOPQ".toCharArray(), null, null);
        assertNull(d.getOwner());
    }

    @Test
    public void DeviceDeviceNameTestTrue() {
        Device d = new Device();
        d.registerDevice(null, null, "ABCDEFGHIJKLMNOPQ", null);
        assertTrue(d.getDeviceName().equals("ABCDEFGHIJKLMNOPQ"));
    }

    @Test
    public void DeviceDeviceNameTestFalse() {
        Device d = new Device();
        d.registerDevice(null, null, "ABCDEFGHIJKLMNOPQ", null);
        assertFalse(d.getDeviceName().equals("faesbters"));
    }

    @Test
    public void DeviceDeviceNameTestNullOne() {
        Device d = new Device();
        d.registerDevice(null, null, "ABCDEFGHIJKLMNOPQ", null);
        assertNull(d.getSerialNumber());
    }

    @Test
    public void DeviceDeviceNameTestNullTwo() {
        Device d = new Device();
        d.registerDevice(null, null, "ABCDEFGHIJKLMNOPQ", null);
        assertNull(d.getMacAddress());
    }

    @Test
    public void DeviceDeviceNameTestNullThree() {
        Device d = new Device();
        d.registerDevice(null, null, "ABCDEFGHIJKLMNOPQ", null);
        assertNull(d.getOwner());
    }

    @Test
    public void DeviceOwnerTestTrue() {
        Device d = new Device();
        d.registerDevice(null, null, null, "ABCDEFGHIJKLMNOPQ");
        assertTrue(d.getOwner().equals("ABCDEFGHIJKLMNOPQ"));
    }

    @Test
    public void DeviceOwnerTestFalse() {
        Device d = new Device();
        d.registerDevice(null, null, null, "ABCDEFGHIJKLMNOPQ");
        assertFalse(d.getOwner().equals("asrtge"));
    }

    @Test
    public void DeviceOwnerTestNullOne() {
        Device d = new Device();
        d.registerDevice(null, null, null, "ABCDEFGHIJKLMNOPQ");
        assertNull(d.getSerialNumber());
    }

    @Test
    public void DeviceOwnerTestNullTwo() {
        Device d = new Device();
        d.registerDevice(null, null, null, "ABCDEFGHIJKLMNOPQ");
        assertNull(d.getMacAddress());
    }

    @Test
    public void DeviceOwnerTestNullThree() {
        Device d = new Device();
        d.registerDevice(null, null, null, "ABCDEFGHIJKLMNOPQ");
        assertNull(d.getDeviceName());
    }
}
