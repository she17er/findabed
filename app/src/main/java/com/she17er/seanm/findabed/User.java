package com.she17er.seanm.findabed;


/**
 * Represents a single user
 */
public class User {
    private String username;
    private boolean booked;
    private int loginTimes;

    public User(String username, boolean booked){
        this.username = username;
        this.booked = booked;
    }

    public User(){}

    public void setBooked(boolean status) {
        this.booked = status;
    }

    public void loginTrial() {
        this.loginTimes++;
    }
}
