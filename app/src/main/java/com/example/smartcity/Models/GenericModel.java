package com.example.smartcity.Models;

public class GenericModel<T> {

    T object;
    public GenericModel(T object){
        this.object = object;
    }

    public T getObject(){
        return this.object;
    }
}
