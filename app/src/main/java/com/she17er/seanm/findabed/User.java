package com.she17er.seanm.findabed;


/**
 * Represents a single user
 */
public class User {
    private String username;
    private boolean booked;
    private int loginTimes;

    /**
     * Creates a new user object
     *
     * @param username The username of the user
     * @param booked Whether the user has booked a bed or not
     */
    public User(String username, boolean booked){
        this.username = username;
        this.booked = booked;
    }

    /**
     * Constructs a new user
     */
    public User(){}

    /**
     * Sets a user as having booked a bed
     * @param status True if booked, false otherwise
     */
    public final void setBooked(boolean status) {
        this.booked = status;
    }

    /**
     * Gets the number of times a user has tried to login
     * @return Number of failed logins for this user
     */
    public final int getLoginTimes(){
        return loginTimes;
    }

    /**
     * Increases the number of "failed logins" for this user
     */
    public final void loginTrial() {
        this.loginTimes++;
    }
}
