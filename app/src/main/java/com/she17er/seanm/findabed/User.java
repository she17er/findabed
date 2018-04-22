package com.she17er.seanm.findabed;


/**
 * Represents a single user
 */
class User {
    private String username;
    private boolean booked;
    private int loginTimes;

    /**
     * Creates a new user object
     *
     * @param username The username of the user
     * @param booked Whether the user has booked a bed or not
     */
    User(final String username, final boolean booked) {
        super();
        this.username = username;
        this.booked = booked;
    }

    /**
     * Constructs a new user
     */
    User() {
        super();
    }

    /**
     * Sets a user as having booked a bed
     * @param status True if booked, false otherwise
     */
    public void setBooked(final boolean status) {
        booked = status;
    }

    /**
     * Gets the number of times a user has tried to login
     * @return Number of failed logins for this user
     */
    public int getLoginTimes(){
        return this.loginTimes;
    }

    /**
     * Increases the number of "failed logins" for this user
     */
    public void loginTrial() {
        loginTimes++;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + this.username + '\'' +
                ", booked=" + this.booked +
                ", loginTimes=" + this.loginTimes +
                '}';
    }
}
