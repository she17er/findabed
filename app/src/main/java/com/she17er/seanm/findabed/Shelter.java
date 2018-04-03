package com.she17er.seanm.findabed;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a given shelter
 *
 * @author elissa huang
 * @edited by sean walsh
 * @version 1.3
 */

public class Shelter implements Parcelable{
    private String _id;
    private String name;
    private int capacity;
    private String gender;
    private String ageRange;
    private String restrictions;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;
    private int currentCapacity;

    public Shelter(ArrayList<String> tokens) {
        setName(tokens.get(1));
        setCapacity(tokens.get(2));
        setGenderandAge(tokens.get(3));
        setLongitude(tokens.get(4));
        setLatitude(tokens.get(5));
        setAddress(tokens.get(6));
        setPhoneNumber(tokens.get(8));
        setCurrentCapacity(tokens.get(9));
        //set_id(tokens.get(10)); add this after finish the csv parsing algorithm
    }
    public Shelter(Parcel in) {
        String throwaway = in.readString();
        setName(in.readString());
        setCapacity(in.readString());
        setGenderandAge(in.readString());
        setLongitude(in.readString());
        setLatitude(in.readString());
        setAddress(in.readString());
        setPhoneNumber(in.readString());
        String throwaway2 = in.readString();
        setCurrentCapacity(in.readString());
    }
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(Integer.toString(capacity));
        dest.writeString(ageRange);
        dest.writeString(restrictions);
        dest.writeString(Double.toString(longitude));
        dest.writeString(Double.toString(latitude));
        dest.writeString(address);
        dest.writeString(phoneNumber);
        dest.writeString(Integer.toString(currentCapacity));
    }
    public static final Parcelable.Creator<Shelter> CREATOR = new Parcelable.Creator<Shelter>()
    {
        public Shelter createFromParcel(Parcel in)
        {
            return new Shelter(in);
        }
        public Shelter[] newArray(int size)
        {
            return new Shelter[size];
        }
    };
    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.replaceAll(";", ",");
        this.name = name;

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(String s) {
        s = s.replaceAll(";", ",");
        if (s == null || s.equals(" ")) {
            this.capacity = 0;
        } else {
            this.capacity = Integer.parseInt(s);
        }
    }

    public String getGender() {
        return gender;
    }

    public String getAgeRange() { return ageRange; }

    public String getRestrictions() { return restrictions; }

    public void setCurrentCapacity(String currentCapacity) {
        currentCapacity = currentCapacity.replaceAll(";", ",");
        this.currentCapacity = Integer.parseInt(currentCapacity);
    }

    public void setGenderandAge(String gender) {
        restrictions = gender.toLowerCase();
        if (restrictions.equals("")) {
            restrictions = "N/A";
        }

        gender = gender.replaceAll(";", ",").toLowerCase();
        if (gender.contains("women")) {
            this.gender = "women";
        } else if (gender.contains("men")) {
            this.gender = "men ";
        } else {
            this.gender = "any gender";
        }

        ageRange = "";
        if (gender.contains("newborn")) {
            ageRange += "newborns, ";
        }
        if (gender.contains("children")) {
            ageRange += "children, ";
        }
        if (gender.contains("young adults")) {
            ageRange += "young adults, ";
        }
        if (!gender.contains("newborn") && !gender.contains("children") && !gender.contains("young adults")) {
            ageRange = "any age";
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

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void set_id(String _id) {this._id = _id;}

    public String get_id() {return this._id;}

    public void incrementCurrentCapacity() {
        currentCapacity += 1;
    }

    public void decrementCurrentCapacity() {
        currentCapacity -= 1;
    }
}