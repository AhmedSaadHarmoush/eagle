package com.example.ahmed.eagletech.Instances;

import java.io.Serializable;

/**
 * Created by ahmed on 8/14/2016.
 */

public class User implements Serializable{
    private String id;


    private String username;
    private String name;
    private Job job;
    private Branch branch;
    private String type;

    public User(Branch branch, String id, Job job, String name, String type, String username) {
        this.branch = branch;
        this.id = id;
        this.job = job;
        this.name = name;
        this.type = type;
        this.username = username;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User() {
    }


    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
