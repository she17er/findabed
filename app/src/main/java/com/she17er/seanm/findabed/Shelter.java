package com.she17er.seanm.findabed;

/**
 * Represents a given shelter
 *
 * @author elissa huang
 * @version 1.0
 */

public class Shelter {
    private String name;
    private String capacity;
    private String gender;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;

    public Shelter(String[] tokens) {
        setName(tokens[1]);
        setCapacity(tokens[2]);
        setGender(tokens[3]);
        setLongitude(tokens[4]);
        setLatitude(tokens[5]);
        setAddress(tokens[6]);
        setPhoneNumber(tokens[8]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String s) {
        if (s == null) {
            this.capacity = "0 available";
        } else {
            this.capacity = s + " available";
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
