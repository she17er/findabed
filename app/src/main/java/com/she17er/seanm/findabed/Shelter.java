package com.she17er.seanm.findabed;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Represents a given shelter
 */

public class Shelter implements Parcelable {

    //All shelter parameters
    private String _id;
    private static int count;
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
    public Shelter() {
        super();
    }

    /**
     * Gets the string representing a shelter, cotaining both its position and current
     * capacity
     *
     * @return Shelter represented as a string
     */
    @Override
    public String toString() {
        return "Shelter{" +
                "_id='" + this._id + '\'' +
                ", name='" + this.name + '\'' +
                ", capacity=" + this.capacity +
                ", gender='" + this.gender + '\'' +
                ", ageRange='" + this.ageRange + '\'' +
                ", restrictions='" + this.restrictions + '\'' +
                ", longitude=" + this.longitude +
                ", latitude=" + this.latitude +
                ", address='" + this.address + '\'' +
                ", phoneNumber='" + this.phoneNumber + '\'' +
                ", currentCapacity=" + this.currentCapacity +
                ", backendID='" + this.backendID + '\'' +
                '}';
    }

    /**
     * Constructs a new shelter with appropriate parameters
     * @param tokens The raw text to be converted to each parameter for this shelter
     */
    public Shelter(final List<String> tokens) {
        super();
        this.setName(tokens.get(1));
        this.setCapacity(tokens.get(2));
        this.setGenderAndAge(tokens.get(3));
        this.setLongitude(tokens.get(4));
        this.setLatitude(tokens.get(5));
        this.setAddress(tokens.get(6));
        this.setPhoneNumber(tokens.get(8));
        this.setCurrentCapacity(tokens.get(9));
        this._id = Integer.toString(Shelter.count);
        Shelter.count++;
//        setBackendID(tokens.get(10));
//        set_id();
        //set_id(tokens.get(10)); //add this after finish the csv parsing algorithm
    }

    /**
     * Generates a shelter using a parcel instead of the CSV reader
     * @param in The parcel passed in from MapScreen (and JSON reader maybe?)
     */
    private Shelter(final Parcel in) {
        super();
        this.set_id(in.readString());
        this.setName(in.readString());
        this.setCapacity(in.readString());
        this.setGenderAndAge(in.readString() + in.readString());
        this.setLongitude(in.readString());
        this.setLatitude(in.readString());
        this.setAddress(in.readString());
        this.setPhoneNumber(in.readString());
        this.setCurrentCapacity(in.readString());
//        set_id();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeString(this._id);
        parcel.writeString(this.name);
        parcel.writeString(Integer.toString(this.capacity));
        parcel.writeString(this.ageRange);
        parcel.writeString(this.restrictions);
        parcel.writeString(Double.toString(this.longitude));
        parcel.writeString(Double.toString(this.latitude));
        parcel.writeString(this.address);
        parcel.writeString(this.phoneNumber);
        parcel.writeString(Integer.toString(this.currentCapacity));
    }


    public static final Creator<Shelter> CREATOR = new Creator<Shelter>()
    {
        @Override
        public Shelter createFromParcel(final Parcel parcel)
        {
            return new Shelter(parcel);
        }
        @Override
        public Shelter[] newArray(final int i)
        {
            return new Shelter[i];
        }
    };

    /**
     * Gets a shelter's name
     * @return The shelter name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets a shelter's name
     * @param name The shelter's name
     */
    public void setName(String name) {
        name.replaceAll(";", ",");
        this.name = name;

    }

    /**
     * Gets a shelter's capacity
     * @return Shelter capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Sets a shelter's capacity
     * @param s The shelter's capacity
     */
    public void setCapacity(String s) {
        s = s.replaceAll(";", ",");
        if ((s == null) || " ".equals(s)) {
            capacity = 0;
        } else {
            capacity = Integer.parseInt(s);
        }
    }

    /**
     * Gets a shelter's gender restrictions
     * @return The shelter's gender restrictions
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Gets a shelters age range
     * @return The shelter's age range
     */
    public String getAgeRange() { return this.ageRange; }

    /**
     * Gets a shelters restrictions
     * @return The shelter's restrictions
     */
    public String getRestrictions() { return this.restrictions; }

    /**
     * Sets a shelters current capacity
     * @param currentCapacity The shelter's current capacity
     */
    public void setCurrentCapacity(String currentCapacity) {
        currentCapacity.replaceAll(";", ",");
        this.currentCapacity = Integer.parseInt(currentCapacity);
    }

    /**
     * Sets the new gender restrictions
     * @param gender The updated gender restrictions
     */
    public void setGenderAndAge(String gender) {
        this.restrictions = gender.toLowerCase();
        if ("".equals(this.restrictions)) {
            this.restrictions = "N/A";
        }

        gender = gender.replaceAll(";", ",").toLowerCase();
        if (gender.contains("women")) {
            this.gender = "women";
        } else if (gender.contains("men")) {
            this.gender = "men";
        } else {
            this.gender = "any gender";
        }

        this.ageRange = "";
        if (gender.contains("newborn")) {
            this.ageRange += "newborns, ";
        }
        if (gender.contains("children")) {
            this.ageRange += "children, ";
        }
        if (gender.contains("young adults")) {
            this.ageRange += "young adults, ";
        }
        if (!gender.contains("newborn") && !gender.contains("children") && !gender
                .contains("young adults")) {
            this.ageRange = "any age";
        }
    }

    /**
     * Gets a shelter's longitude
     * @return Shelter's longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Sets a shelter's longitude
     * @param longitude The shelter's longitude
     */
    public void setLongitude(String longitude) {
        longitude.replaceAll(";", ",");
        try {
            this.longitude = Double.valueOf(longitude).doubleValue();
        } catch (final RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a shelter's latitude
     * @return The shelter's latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Sets a shelter's latitude
     * @param latitude The shelter's latitude
     */
    public void setLatitude(final String latitude) {
        if (latitude == null) {
            throw new IllegalArgumentException("Latitude entered was null");
        }
        String latitude1 = latitude.replaceAll(";", "");
        latitude1 = latitude.replaceAll(",", "");
        try {
            final double newLat = Double.valueOf(latitude1).doubleValue();
            if ((newLat > 90.0) || (newLat < -90.0)) {
                throw new IllegalArgumentException("Illegal Latitude Entered: " + newLat);
            } else {
                this.latitude = newLat;
            }
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            throw new NumberFormatException("Number exception");
        }
    }

    /**
     * Gets a shelter's address
     * @return Shelter address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets a shelter's address
     * @param address Shelter address
     */
    public void setAddress(String address) {
        address.replaceAll(";", ",");
        this.address = address;
    }

    /**
     * Gets a shelter's phone number
     * @return Shelter phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets a shelter's phone number
     * @param phoneNumber Shelter phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        phoneNumber.replaceAll(";", ",");
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets a shelters current capacity
     * @return Current capacity
     */
    public int getCurrentCapacity() {
        return this.currentCapacity;
    }

    /**
     * Sets a shelters ArrayList position/ ID
     * @param id Shelter's position
     */
    public void set_id(final String id) {
//        int pos = 0;
//        while (!Dashboard.masterShelters.get(pos).getName().equals(this.getName())) {
//            pos++;
//        }
//        _id = "" + pos;
        this._id = id;
    }

    /**
     * Gets a shelter's ArrayList position ID
     * @return Shelter's position
     */
    public String get_id() {return _id;}

    /**
     * Sets a shelter's backend ID
     * @param id Shelter's backend ID
     */
    public void setBackendID(final String id) {
        this.backendID = id;
    }

    /**
     * Gets a shelter's backend ID
     * @return Shelter's backend ID
     */
    public String getBackendID() {
        return this.backendID;
    }

    /**
     * Increments current shelter capacity
     */
    public void incrementCurrentCapacity() {
        this.currentCapacity += 1;
    }

    /**
     * Decrements current shelter capacity
     */
    public void decrementCurrentCapacity() {
        this.currentCapacity -= 1;
    }

    /**
     * the equals method for this class
     * @param obj The object to compare to
     * @return Whether s is equal to this object
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Shelter)) {
            return false;
        }
        final Shelter that = (Shelter) obj;
        return that.getBackendID().equals(backendID) && getAddress().equals(that
                .getAddress());
    }
}
