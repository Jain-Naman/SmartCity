package com.example.smartcity;

public class Globals {
    public static String currentUser = "admin";
    public static String email = "user";

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        Globals.currentUser = currentUser;
    }
}
