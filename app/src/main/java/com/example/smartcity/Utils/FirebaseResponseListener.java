package com.example.smartcity.Utils;

import com.example.smartcity.Models.TravelModel;

import java.util.List;

public interface FirebaseResponseListener<T> {
    void onCallback(T response);
}
