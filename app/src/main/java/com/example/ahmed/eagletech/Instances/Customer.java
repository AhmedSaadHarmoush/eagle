package com.example.ahmed.eagletech.Instances;

/**
 * Created by ahmed on 8/21/2016.
 */

public class Customer {
    private String customer_id;
    private String customer_city;
    private String responsible_name;
    private User user;

    public String getCustomer_city() {
        return customer_city;
    }

    public void setCustomer_city(String customer_city) {
        this.customer_city = customer_city;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getResponsible_name() {
        return responsible_name;
    }

    public void setResponsible_name(String responsible_name) {
        this.responsible_name = responsible_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
