package com.example.project1;

public class SpecificJob {
    private int imageviewjob;
    private String active;
    private String jobs;
    private  String location;
    SpecificJob(int imageviewjob,String active,String jobs, String location){
        this.imageviewjob=imageviewjob;
        this.active=active;
        this.jobs=jobs;
        this.location=location;

    }

    public int getImageviewjob() {
        return imageviewjob;
    }

    public String getActive() {
        return active;
    }

    public String getJobs() {
        return jobs;
    }

    public String getLocation() {
        return location;
    }
}
