package com.example.mylibrary;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

public class DeviceManager {
    // HashMap to store scanned devices
    private final Map<String, DeviceDetails> devices;

    public DeviceManager() {
        devices = new HashMap<>();
    }

    // Method to receive scanned device information and the edge/message
    public void receiveScannedDevice(String macAddress, int rssi, String deviceName) {
        DeviceDetails deviceDetails = new DeviceDetails(rssi, deviceName);
        devices.put(macAddress, deviceDetails);
    }

    // Method to retrieve devices in the HashMap
    public Map<String, DeviceDetails> getHashMap() {
        return devices;
    }

    // Inner class to hold device information
    public static class DeviceDetails {
        private final int rssi;
        private final String deviceName;

        public DeviceDetails(int rssi, String deviceName) {
            this.rssi = rssi;
            this.deviceName = deviceName;
        }

        public int getRssi() {
            return rssi;
        }

        public String getScanningDevice() {
            return deviceName;
        }

        @NotNull
        @Override
        public String toString() {
            return "Value = {" +
                    "rssi=" + rssi +
                    ", deviceName=" + deviceName +
                    '}';
        }
    }

    // Method to get devices by macAddresses
    public DeviceDetails getDeviceDetails(String macAddress) {
        return devices.get(macAddress);
    }

    // For debugging
    public void logAllDevices() {
        System.out.println("HashMap " + devices);
    }
}
