package com.example.ahmed.eagletech.Instances;

/**
 * Created by ahmed on 8/20/2016.
 */

public class Employee {
    private String emploee_id;
    private String birth_date;
    private String job_role_id;
    private String manager_id;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmploee_id() {
        return emploee_id;
    }

    public void setEmploee_id(String emploee_id) {
        this.emploee_id = emploee_id;
    }

    public String getJob_role_id() {
        return job_role_id;
    }

    public void setJob_role_id(String job_role_id) {
        this.job_role_id = job_role_id;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
}
