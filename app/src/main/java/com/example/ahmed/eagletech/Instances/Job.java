package com.example.ahmed.eagletech.Instances;

/**
 * Created by ahmed on 8/14/2016.
 */
public class Job {
    private String id;
    private String name;

    public Job() {
    }

    public Job(String id, String name) {
        this.id = id;
        this.name = name;
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
