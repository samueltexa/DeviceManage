## Table of Contents

1. [Introduction](#introduction)
2. [Section 1: Testing the Library with Hardcoded Values](#section-1-testing-the-library-with-hardcoded-values)
3. [Section 2: Integration with a Real BLE Application](#section-2-integration-with-a-real-ble-application)
   - [Features](#features)
   - [Prerequisites](#prerequisites)
   - [Integration Steps](#integration-steps)
   - [Using the Device Organizer Library](#using-the-device-organizer-library)

---

## Introduction

This guide provides a step-by-step explanation for integrating and using the **DeviceOrganizer Library** in an Android Java project. The library enables users to manage scanning and broadcasting functionality within their Bluetooth Low Energy (BLE) enabled applications. It is designed to work in Java environments, specifically tailored for Android applications.

This guide is divided into two sections:

- **Section 1**: Testing the library with hardcoded values (for familiarizing with functionality).
- **Section 2**: Integration with a real BLE application (using live scanning and broadcasting data).

By the end of this guide, you will be able to integrate the DeviceOrganizer Library into both test and production Android applications.

---

## Features

- **Receiving Scanned Devices**: Captures scanned device information (MAC address, RSSI, and device name).
- **Building and Managing HashMaps**: Organizes scanned devices into a hashmap format for easy retrieval.

---

## Prerequisites

Before you begin, ensure that your development environment meets the following requirements:

- Java Development Kit (JDK) 8 or higher.
- Recommended IDE: Android Studio, IntelliJ IDEA, or Eclipse.
- Android project set up with Gradle (configured by default in Android Studio).

---

## Integration Steps

### Step 1: Creating Your Android Project

If you haven’t already created your Android project, follow these steps:

1. Open **Android Studio** or **IntelliJ IDEA** and click **Start a New Android Studio Project**.
2. Select an **empty activity** or a template of your choice.
3. Set the project name (e.g., "MyBLEApp") and select **Java** as the language.
4. Choose the minimum SDK version as **API Level 21 (Android 5.0 Lollipop)** or higher.

### Step 2: Configuring GitHub Maven Repository

Before adding the DeviceOrganizer Library as a dependency, configure your project to access the GitHub Maven repository:

1. Open `settings.gradle.kts` or `settings.gradle` in the root of your project.
2. Add the following code snippet to include the GitHub Maven repository:

```groovy
maven {
    url = uri("https://maven.pkg.github.com/samueltexa/DeviceManage")
    credentials {
        username = "xxxxxxx" // replace with your GitHub username
        password = "xxxxxxxxxx" // replace with your GitHub token
    }
}
```

### Step 3: Adding Library Dependency

Now, add the DeviceOrganizer Library to your app’s dependencies:

For **Gradle Kotlin DSL**:
```gradle
implementation("com.example:mylibrary:1.0.1")
```

For **Gradle Groovy DSL**:
```gradle
implementation("com.example:mylibrary:1.0.1")
```

---

## Using the Device Organizer Library

### Section 1: Testing the Library with Hardcoded Values

After adding the library as a dependency, you can begin testing its functionality using hardcoded values:

1. **Import the Library**:
   ```java
   import com.example.mylibrary.DeviceManager;
   ```

2. **Initialize the Library**:
   ```java
   DeviceManager devicemanager = new DeviceManager();
   ```

3. **Pass Parameters to `receiveScannedDevice` Method**:
   This method is used to add scanned device information (like MAC address, RSSI value, etc.) to the list. Pass three parameters:  
   - MAC Address (String)
   - RSSI Value (int)
   - Scanned Device Name (String)

   Example:
   ```java
   devicemanager.receiveScannedDevice("00:11:22:33:44:55", -45, "Device1");
   ```

4. **Log All Scanned Devices**:
   You can log all scanned devices with:
   ```java
   devicemanager.logAllDevices();
   ```

   Sample output:
   ```java
   HashMap {00:1A:2B:3C:4D:5E={rssi=60, deviceName=Device1}, AB-CD-EF-12-34-56={rssi=60, deviceName=Device2}}
   ```

5. **Retrieve Device Details**:
   Retrieve the details of a specific device by passing the MAC address:
   ```java
   devicemanager.getDeviceDetails("00:1A:2B:3C:4D:5E");
   ```

   Sample output:
   ```
   MAC Address: 00:1A:2B:3C:4D:5E
   RSSI: 60
   Device Name: Device1
   ```

---

### Section 2: Integrating DeviceOrganizer Library into a BLE Application

Once you’ve tested the basic functionality, proceed with integrating the library into a fully functional **Master-Slave Scanner** app using live Bluetooth scanning and broadcasting.

1. **Import the Library**:
   ```java
   import com.example.mylibrary.DeviceManager;
   ```

2. **Initialize the Library**:
   ```java
   DeviceManager devicemanager = new DeviceManager();
   ```

3. **Set Up Bluetooth Scanning and Advertising**:
   Use the `receiveScannedDevice` method inside the **scan callback** to process real-time BLE data:
   
   Example:
   ```java
   @Override
   public void onScanResult(int callbackType, ScanResult result) {
       devicemanager.receiveScannedDevice(result.getDevice().getAddress(), result.getRssi(), result.getDevice().getName());
   }
   ```

4. **Log Data in Logcat**:
   Use the `logAllDevices` method to log all devices to Logcat for debugging purposes:
   ```java
   devicemanager.logAllDevices();
   ```

   **User Responsibility**: The DeviceOrganizer Library does not log data by default. You are responsible for logging as required for your application.

---

## Conclusion

This guide demonstrates how to integrate the **DeviceOrganizer Library** into your Android BLE application, both for testing purposes with hardcoded values and real-time usage in a functional BLE app.
