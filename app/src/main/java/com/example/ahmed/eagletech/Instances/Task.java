package com.example.ahmed.eagletech.Instances;

/**
 * Created by ahmed on 8/26/2016.
 */

public class Task {
    private String task_id;
    private String task_num;
    private String task_name;
    private String task_description;
    private Types task_type;
    private Priority priority;
    private Severity severity;
    private Status status;
    private User created_by;
    private String created_at;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_num() {
        return task_num;
    }

    public void setTask_num(String task_num) {
        this.task_num = task_num;
    }

    public Types getTask_type() {
        return task_type;
    }

    public void setTask_type(Types task_type) {
        this.task_type = task_type;
    }
}
