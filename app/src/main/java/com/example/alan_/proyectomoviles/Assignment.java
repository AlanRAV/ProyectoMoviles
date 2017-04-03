package com.example.alan_.proyectomoviles;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class Assignment {

   // public static final String COURSE_KEY = "coursekey";

   // public String key;
    public String assignmentName;
    public String assignmentGrade;
    public String assignmentDueDate;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Assignment() {
    }

    public Assignment(String assignmentName, String assignmentGrade, String assignmentDueDate) {
        this.assignmentName = assignmentName;
        this.assignmentGrade = assignmentGrade;
        this.assignmentDueDate = assignmentDueDate;
    }
}