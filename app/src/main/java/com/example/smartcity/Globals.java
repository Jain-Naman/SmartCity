package com.example.smartcity;

public class Globals {
    public static String currentUser = "admin";

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        Globals.currentUser = currentUser;
    }
}
