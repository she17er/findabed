/* Action types*/

//User values
export const USER_STATUS = "USER_STATUS";
export const CURRENT_USERS = "CURRENT_USER";

//Authentication action types
export const LOGOUT = Symbol("LOGOUT");
export const LOGIN_FAILED = Symbol("LOGIN_FAILED");
export const LOGIN_SUCCESS = Symbol("LOGIN_SUCCESS");
export const REGISTRATION_FAILED = "REGISTRATION_FAILED";
export const REGISTRATION_SUCCESS = "REGISTRATION_SUCCESS";

//Shelter Value
export const SHELTER_NAME = Symbol("SHELTER_NAME");
export const SHELTER_LOCATION = Symbol("SHELTER_LOCATION");
export const CREATE_SHELTER = Symbol("CREATE_SHELTER");
export const SHELTER_LOCATION_CHANGE = Symbol("SHELTER_LOCATION_CHANGE");
export const SHELTER_ACCEPTEDTYPE_CHANGE = Symbol("SHELTER_ACCEPTEDTYPE_CHANGE");
export const SHELTER_CONTACT_CHANGE = Symbol("SHELTER_CONTACT_CHANGE");

//User events
export const LOAD_PENDING_USERS = Symbol("LOAD_PENDING_USERS");
export const LOAD_NEWEST_USERS = Symbol("LOAD_NEWEST_USERS");
export const LOAD_ALL_USERS = Symbol("LOAD_ALL_USERS");
export const UPDATE_CURRENT_USERS = Symbol("UPDATE_CURRENT_USERS");
export const APPROVE_PENDING_USERS= Symbol("APPROVE_PENDING_USERS");
