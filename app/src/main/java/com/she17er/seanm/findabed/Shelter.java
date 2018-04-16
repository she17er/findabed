package com.she17er.seanm.findabed;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a given shelter
 */

public class Shelter implements Parcelable {

    //All shelter parameters
    private String _id;
    private static int count = 0;
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
    private String backendID;

    /**
     * Constructs a new shelter without any params (redundant)
     */
    public Shelter(){}

    /**
     * Constructs a new shelter with appropriate parameters
     * @param tokens The raw text to be converted to each parameter for this shelter
     */
    public Shelter(List<String> tokens) {
        setName(tokens.get(1));
        setCapacity(tokens.get(2));
        setGenderAndAge(tokens.get(3));
        setLongitude(tokens.get(4));
        setLatitude(tokens.get(5));
        setAddress(tokens.get(6));
        setPhoneNumber(tokens.get(8));
        setCurrentCapacity(tokens.get(9));
        _id = Integer.toString(count);
        count++;
//        setBackendID(tokens.get(10));
//        set_id();
        //set_id(tokens.get(10)); //add this after finish the csv parsing algorithm
    }

    /**
     * Generates a shelter using a parcel instead of the CSV reader
     * @param in The parcel passed in from MapScreen (and JSON reader maybe?)
     */
    private Shelter(Parcel in) {
        set_id(in.readString());
        setName(in.readString());
        setCapacity(in.readString());
        setGenderAndAge(in.readString() + in.readString());
        setLongitude(in.readString());
        setLatitude(in.readString());
        setAddress(in.readString());
        setPhoneNumber(in.readString());
        setCurrentCapacity(in.readString());
//        set_id();
    }

    @Override
    public final int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public final void writeToParcel(Parcel dest, int flags) {
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
        @Override
        public Shelter createFromParcel(Parcel in)
        {
            return new Shelter(in);
        }
        @Override
        public Shelter[] newArray(int size)
        {
            return new Shelter[size];
        }
    };

    /**
     * Gets a shelter's name
     * @return The shelter name
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets a shelter's name
     * @param name The shelter's name
     */
    public final void setName(String name) {
        name = name.replaceAll(";", ",");
        this.name = name;

    }

    /**
     * Gets a shelter's capacity
     * @return Shelter capacity
     */
    public final int getCapacity() {
        return capacity;
    }

    /**
     * Sets a shelter's capacity
     * @param s The shelter's capacity
     */
    public final void setCapacity(String s) {
        s = s.replaceAll(";", ",");
        if ((s == null) || s.equals(" ")) {
            this.capacity = 0;
        } else {
            this.capacity = Integer.parseInt(s);
        }
    }

    /**
     * Gets a shelter's gender restrictions
     * @return The shelter's gender restrictions
     */
    public final String getGender() {
        return gender;
    }

    /**
     * Gets a shelters age range
     * @return The shelter's age range
     */
    public final String getAgeRange() { return ageRange; }

    /**
     * Gets a shelters restrictions
     * @return The shelter's restrictions
     */
    public final String getRestrictions() { return restrictions; }

    /**
     * Sets a shelters current capacity
     * @param currentCapacity The shelter's current capacity
     */
    public final void setCurrentCapacity(String currentCapacity) {
        currentCapacity = currentCapacity.replaceAll(";", ",");
        this.currentCapacity = Integer.parseInt(currentCapacity);
    }

    /**
     * Sets the new gender restrictions
     * @param gender The updated gender restrictions
     */
    public final void setGenderAndAge(String gender) {
        restrictions = gender.toLowerCase();
        if (restrictions.equals("")) {
            restrictions = "N/A";
        }

        gender = gender.replaceAll(";", ",").toLowerCase();
        if (gender.contains("women")) {
            this.gender = "women";
        } else if (gender.contains("men")) {
            this.gender = "men";
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

    /**
     * Gets a shelter's longitude
     * @return Shelter's longitude
     */
    public final double getLongitude() {
        return longitude;
    }

    /**
     * Sets a shelter's longitude
     * @param longitude The shelter's longitude
     */
    public final void setLongitude(String longitude) {
        longitude = longitude.replaceAll(";", ",");
        try {
            this.longitude = Double.valueOf(longitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a shelter's latitude
     * @return The shelter's latitude
     */
    public final double getLatitude() {
        return latitude;
    }

    /**
     * Sets a shelter's latitude
     * @param latitude The shelter's latitude
     */
    public final void setLatitude(String latitude) {
        latitude = latitude.replaceAll(";", ",");
        try {
            this.latitude = Double.valueOf(latitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a shelter's address
     * @return Shelter address
     */
    public final String getAddress() {
        return address;
    }

    /**
     * Sets a shelter's address
     * @param address Shelter address
     */
    public final void setAddress(String address) {
        address = address.replaceAll(";", ",");
        this.address = address;
    }

    /**
     * Gets a shelter's phone number
     * @return Shelter phone number
     */
    public final String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets a shelter's phone number
     * @param phoneNumber Shelter phone number
     */
    public final void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll(";", ",");
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets a shelters current capacity
     * @return Current capacity
     */
    public final int getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * Sets a shelters ArrayList position/ ID
     * @param id Shelter's position
     */
    public final void set_id(String id) {
//        int pos = 0;
//        while (!Dashboard.masterShelters.get(pos).getName().equals(this.getName())) {
//            pos++;
//        }
//        _id = "" + pos;
        _id = id;
    }

    /**
     * Gets a shelter's ArrayList position ID
     * @return Shelter's position
     */
    public final String get_id() {return this._id;}

    /**
     * Sets a shelter's backend ID
     * @param id Shelter's backend ID
     */
    public final void setBackendID(String id) {
        backendID = id;
    }

    /**
     * Gets a shelter's backend ID
     * @return Shelter's backend ID
     */
    public final String getBackendID() {
        return backendID;
    }

    /**
     * Increments current shelter capacity
     */
    public final void incrementCurrentCapacity() {
        currentCapacity += 1;
    }

    /**
     * Decrements current shelter capacity
     */
    public final void decrementCurrentCapacity() {
        currentCapacity -= 1;
    }

    /**
     * Gets the string representing a shelter, cotaining both its position and current
     * capacity
     * @return Shelter represented as a string
     */
    public final String toString() {
        return _id + currentCapacity;
    }

    /**
     * the equals method for this class
     * @param s
     * @return
     */
    @Override
    public boolean equals(Object s) {
        if (s == null) {
            return false;
        }
        if (!(s instanceof Shelter)) {
            return false;
        }
        Shelter that = (Shelter) s;
        return that.getBackendID().equals(this.backendID) && this.getAddress().equals(that.getAddress());
    }
}
