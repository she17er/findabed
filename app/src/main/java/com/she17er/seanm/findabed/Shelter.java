package com.she17er.seanm.findabed;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a given shelter
 *
 * @author elissa huang
 * @version 1.3
 */

public class Shelter {
    private String name;
    private String capacity;
    private String gender;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;

    public Shelter(ArrayList<String> tokens) {
        setName(tokens.get(1));
        setCapacity(tokens.get(2));
        setGender(tokens.get(3));
        setLongitude(tokens.get(4));
        setLatitude(tokens.get(5));
        setAddress(tokens.get(6));
        setPhoneNumber(tokens.get(8));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.replaceAll(";", ",");
        this.name = name;

    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String s) {
        s = s.replaceAll(";", ",");
        if (s == null || s.equals(" ")) {
            this.capacity = "0 available";
        } else {
            this.capacity = s + " available";
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        gender = gender.replaceAll(";", ",");
        if (gender.toLowerCase().contains("women")) {
            if (gender.toLowerCase().contains("men")) {
                this.gender = "all";
            } else {
                this.gender = "women";
            }
        } else if (gender.toLowerCase().contains("men")) {
            this.gender = "men";
        } else if (gender.toLowerCase().contains("any")){
            this.gender = "all";
        } else {
            this.gender = "N/A";
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        longitude = longitude.replaceAll(";", ",");
        try {
            this.longitude = Double.valueOf(longitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        latitude = latitude.replaceAll(";", ",");
        try {
            this.latitude = Double.valueOf(latitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address.replaceAll(";", ",");
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll(";", ",");
        this.phoneNumber = phoneNumber;
    }
}
