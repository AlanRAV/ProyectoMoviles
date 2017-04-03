package com.example.alan_.proyectomoviles;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class SaveCourseActivity extends AppCompatActivity {

    private static final int ASSIGNMENT_REQUEST_CODE = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView crsDetails;
    private EditText inputTitle, inputDay, inputTime;
    private Button btnSaveCourse;
    private Button btnAssignments;
    private Button dltCourse;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

  //  private String userId;
    private String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_course);


        // Displaying toolbar icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        crsDetails = (TextView) findViewById(R.id.txt_course);
        inputTitle = (EditText) findViewById(R.id.courseTitle);
        inputDay = (EditText) findViewById(R.id.courseDay);
        inputTime = (EditText) findViewById(R.id.courseTime);
        btnAssignments = (Button) findViewById(R.id.btn_assignments);
        dltCourse = (Button) findViewById(R.id.btn_deleteCourse);

        btnSaveCourse = (Button) findViewById(R.id.btn_saveCourse);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'courses' node
        mFirebaseDatabase = mFirebaseInstance.getReference("courses");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        // Save / update the user
        btnSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseTitle = inputTitle.getText().toString();
                String courseDay = inputDay.getText().toString();
                String courseTime = inputTime.getText().toString();

                // Check for already existed userId
                if (TextUtils.isEmpty(courseId)) {
                    createCourse(courseTitle, courseDay, courseTime);
                } else {
                    updateCourse(courseTitle, courseDay, courseTime);
                }
            }
        });

        dltCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseTitle = inputTitle.getText().toString();
                String courseDay = inputDay.getText().toString();
                String courseTime = inputTime.getText().toString();

                // Check for already existed userId
                deleteCourse(courseTitle, courseDay, courseTime);

            }
        });

        btnAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (SaveCourseActivity.this, AssignmentActivity.class);
                intent.putExtra("COURSE_ID", courseId);

                startActivityForResult(intent, ASSIGNMENT_REQUEST_CODE);


            }
        });

        toggleButton();
    }

    // Changing button text
    private void toggleButton() {
        if (TextUtils.isEmpty(courseId)) {
            btnSaveCourse.setText("Save Course");
        } else {
            btnSaveCourse.setText("Update");
        }
    }

    /**
     * Creating new user node under 'users'
     */
   /* private void createUser(String name, String email) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        User user = new User(name, email);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }
*/
    private void createCourse(String courseTittle, String courseDay, String courseTime) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(courseId)) {
            courseId = mFirebaseDatabase.push().getKey();
        }

        Course course = new Course(courseTittle, courseDay, courseTime);

        mFirebaseDatabase.child(courseId).setValue(course);

        addCourseChangeListener();
    }

  /*  private void createAssignment(String assignmentName, String assignmentGrade, String assignmentDueDate) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        Assignment assignment = new Assignment(assignmentName, assignmentGrade, assignmentDueDate);

        mFirebaseDatabase.child(userId).setValue(assignment);

        addUserChangeListener();
    }
*/
    /**
     * User data change listener
     */
    private void addCourseChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(courseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Course course = dataSnapshot.getValue(Course.class);

                // Check for null
                if (course == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "Course data is changed!" + course.courseTittle + ", " + course.courseDay + ", " + course.courseTime);

                // Display newly updated name and email
                crsDetails.setText(course.courseTittle + ", " + course.courseDay + ", " + course.courseTime);

                // clear edit text
                inputTitle.setText("");
                inputDay.setText("");
                inputTime.setText("");


                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateCourse(String courseTittle, String courseDay, String courseTime) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(courseTittle))
            mFirebaseDatabase.child(courseId).child("courseTittle").setValue(courseTittle);

        if (!TextUtils.isEmpty(courseDay))
            mFirebaseDatabase.child(courseId).child("courseDay").setValue(courseDay);
        if (!TextUtils.isEmpty(courseTime))
            mFirebaseDatabase.child(courseId).child("courseTime").setValue(courseTime);


    }

    private  void deleteCourse (String courseTittle, String courseDay, String courseTime){
            // updating the user via child nodes
            if (!TextUtils.isEmpty(courseTittle))
                mFirebaseDatabase.child(courseId).child("courseTittle").setValue(null);

            if (!TextUtils.isEmpty(courseDay))
                mFirebaseDatabase.child(courseId).child("courseDay").setValue(null);
            if (!TextUtils.isEmpty(courseTime))
                mFirebaseDatabase.child(courseId).child("courseTime").setValue(null);


        }



    }