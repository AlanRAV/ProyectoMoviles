package com.example.alan_.proyectomoviles;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddGroup extends AppCompatActivity {


    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;



    private static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String groupId;
    private EditText inputGrupo, inputGrado;
    ArrayList<String> list = new ArrayList<String>();

    private Button btnSaveGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        list.add("");



        mDrawerList = (ListView)findViewById(R.id.drawer);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //instantiate custom adapter
        MyCustomAdapter adapter = new MyCustomAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.leads_list);
        lView.setAdapter(adapter);
        inputGrado = (EditText) findViewById(R.id.editTextGrado);
        inputGrupo = (EditText) findViewById(R.id.txtGrupoLetter);


        btnSaveGroup = (Button) findViewById(R.id.buttonSaveAssistency);


        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'courses' node
        mFirebaseDatabase = mFirebaseInstance.getReference("groups");

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

        btnSaveGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int groupGrade = Integer.parseInt(inputGrado.getText().toString());
                String groupLetter = inputGrupo.getText().toString();
                //String courseTime = inputTime.getText().toString();

                list.remove(0);

                // Check for already existed userId
              //  if (TextUtils.isEmpty(groupId)) {
                    createGroup(groupGrade, groupLetter,  list);
            /*} else {
                    updateGroup(groupGrade, groupLetter, list);
                }
                */


            }
        });


    }



    private void createGroup(int groupGrade, String groupLetter,  ArrayList<String> list) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
       // if (TextUtils.isEmpty(groupId)) {
            //groupId = mFirebaseDatabase.push().getKey();
       // }

        Group group = new Group(groupGrade, groupLetter, list);

        mFirebaseDatabase//.child(groupId)
                .setValue(group);

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
        mFirebaseDatabase//.child(groupId),
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Group group = dataSnapshot.getValue(Group.class);

                // Check for null
                if (group == null) {
                    Log.e(TAG, "Group data is null!");
                    return;
                }

                Log.e(TAG, "Group data is changed!" + group.groupGrade + ", " + group.groupLetter + ", " + group.groupStudents);

                // Display newly updated name and email
               // crsDetails.setText(course.courseTittle + ", " + course.courseDay + ", " + course.courseTime);

                // clear edit text
                //inputTitle.setText("");
                //inputDay.setText("");
                //inputTime.setText("");


                //toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read group", error.toException());
            }
        });
    }

    private void updateGroup(int groupGrade, String groupLetter,  ArrayList<String> list) {
        // updating the user via child nodes
       // if (!TextUtils.isEmpty(groupGrade))
            mFirebaseDatabase//.child(groupId)
                    .child("groupGrade").setValue(groupGrade);

        //if (!TextUtils.isEmpty(groupLetter))
            mFirebaseDatabase//.child(groupId)
                    .child("groupLetter").setValue(groupLetter);
        //if (!TextUtils.isEmpty(list))
            mFirebaseDatabase//.child(groupId)
                    .child("groupStudents").setValue(list);


    }

    private  void deleteGroup (String groupGrade){
        // updating the user via child nodes
        if (!TextUtils.isEmpty(groupGrade))
            mFirebaseDatabase//.child(groupId)
                    .child("groupGrade").setValue(null);
            mFirebaseDatabase//.child(groupId)
                    .child("groupLetter").setValue(null);
            mFirebaseDatabase//.child(groupId)
                    .child("groupStudents").setValue(null);


    }


    private void addDrawerItems() {
        String[] osArray = { "Inicio", "Lista", "Actividades" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddGroup.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

