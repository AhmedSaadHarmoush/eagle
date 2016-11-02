package com.example.ahmed.eagletech.Instances;

/**
 * Created by ahmed on 8/14/2016.
 */
public class Branch {
    private String id;
    private String name;
    private String address;
    private String city;

    public Branch() {
    }

    public Branch(String address, String city, String id, String name) {
        this.address = address;
        this.city = city;
        this.id = id;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
