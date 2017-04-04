package com.example.alan_.proyectomoviles;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class Assignment {

   // public static final String COURSE_KEY = "coursekey";

   // public String key;
    public String assignmentName;
    public String assignmentGrade;
    public Long assignmentTimeMil;
    public String assignmentDate;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Assignment() {
    }

    public Assignment(String assignmentName, String assignmentGrade, Long assignmentTimeMil, String assignmentDate) {
        this.assignmentName = assignmentName;
        this.assignmentGrade = assignmentGrade;
        this.assignmentTimeMil = assignmentTimeMil;
        this.assignmentDate = assignmentDate;
    }
}