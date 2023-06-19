package api.homework.model;

public class LoginBodyModel {
    //{
    //    "name": "morpheus",
    //    "job": "leader"
    //}

    private String name, job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
