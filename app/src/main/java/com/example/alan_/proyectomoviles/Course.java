package com.example.alan_.proyectomoviles;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class Course {

    public String courseTittle;
    public String courseDay;
    public String courseTime;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Course() {
    }

    public Course(String courseTittle, String courseDay, String courseTime) {
        this.courseTittle = courseTittle;
        this.courseDay = courseDay;
        this.courseTime = courseTime;
    }
}