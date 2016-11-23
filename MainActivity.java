package com.example.zach.myapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.google.firebase.database.*;

import java.util.*;
import java.util.LinkedHashMap;
import java.util.HashMap;

import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import static com.example.zach.myapp2.R.id.*;


public class MainActivity extends AppCompatActivity {
  /* BUTTONS/TEXT BARS WE WILL USE LATER
    TextView mConditionTextView;
    Button mCondition1;
    Button mCondition2;
    Button mCondition3;
    Button mCondition4; //push button
    EditText mEditText;
    EditText mEditText2;
*/
    String name,pass,web;
    int countHmap=1; // a counter for hashmap
    //a hash map for storing data
    //HashMap<Integer, User> hmap = new HashMap<Integer, User>();
    //HashMap hmap = new HashMap();




    //***REFERENCE for app to access database DATA***
    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mPostRef=mRootRef.child("UserEntriesInfo"); //to push data using this node path
    DatabaseReference mPostRef1=mRootRef.child("Websites");
    DatabaseReference mPostRef2=mRootRef.child("User's Names");
    DatabaseReference mConditionRef=mRootRef.child("username");
    DatabaseReference mConditionRef1=mRootRef.child("password");
    DatabaseReference mConditionRef2=mRootRef.child("website_entry");
    DatabaseReference newPostRef = mPostRef.push();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference ref = database.getReference("server/saving-data/fireblog");

    //DatabaseReference ref = mRootRef("server/saving-data/fireblog");
    //DatabaseReference usersRef = ref.child("users");

    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //***HASH MAP ATTMEPT ERROR BEGINS HERE***
        //**********TRYING TO CREATE CHILD NODES *************

        //create a hash map to store data on database
       /* HashMap<String, User> users = new LinkedHashMap<String,User>();

        //puts 'alanisawsome' as starting node and adds a new User -->into a HashMap
        users.put("alanisawesome", new User("June 23, 1912", "Alan Turing"));
        users.put("gracehop", new User("December 9, 1906", "Grace Hopper", "test"));
        ref.setValue(users);*/
        //***ERROR ENDS HERE***


        //Get UI elements
        final TextView mConditionTextView = (TextView) findViewById(textView);
        final EditText mEditText=(EditText) findViewById(editText); //name input textbar
        final EditText mEditText2=(EditText) findViewById(editText2); //password input textbar
        final EditText mEditText3=(EditText) findViewById(editText3); //website input textbar
        final Button mCondition1 = (Button) findViewById(R.id.condition1);
        final Button mCondition2 = (Button) findViewById(R.id.condition2);
        final Button mCondition3 = (Button) findViewById(R.id.condition3);
        final Button mCondition4 = (Button) findViewById(R.id.reset_all);
        final Button mCondition5 = (Button) findViewById(R.id.push_button);
       // final Button Hello = (Button) findViewById(R.id.Hello);


        //***Creates the ability to check values on database*****
        //Changes text view to approriate data sent
        final ValueEventListener valueEventListener = mConditionRef.addValueEventListener(new ValueEventListener() {

            @Override
            //gets a snapshot of firebase (pulls down data from database)
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
               // name=dataSnapshot.getValue(String.class); //to store what has been entered
                mConditionTextView.setText(text);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final ValueEventListener valueEventListener1 = mConditionRef1.addValueEventListener(new ValueEventListener() {

            @Override
            //gets a snapshot of firebase (pulls down data from database)
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                pass=dataSnapshot.getValue(String.class);//store password to be pushed later to database
                //notification of change
                mConditionTextView.setText(text);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final ValueEventListener valueEventListener2 = mConditionRef2.addValueEventListener(new ValueEventListener() {

            @Override
            //gets a snapshot of firebase (pulls down data from database)
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                web=dataSnapshot.getValue(String.class);//to store web info to be pushed to database later
                //notification of change
                mConditionTextView.setText(text);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //*********************************************************
        mCondition1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //takes in user input for username
                EditText userNameInput= (EditText) findViewById(editText);
                name=userNameInput.getText().toString();
                //mConditionRef.setValue(userNameInput.getText());
                mConditionTextView.setText("*USERNAME ready to push to FIREBASE!*.");
                //reset username text field
                mEditText.setText("");


            }
        });



        mCondition2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //takes in user input for password
                EditText passwordInput= (EditText) findViewById(editText2);
                pass=passwordInput.getText().toString();
               // mConditionRef1.setValue(passwordInput.getText());
                mConditionTextView.setText("*PASSWORD ready to push to FIREBASE!*");
                //reset password text field
                mEditText2.setText("");
            }
        });


        mCondition3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //takes in input for web entry
                EditText webInput=(EditText) findViewById(editText3);
                web=webInput.getText().toString();
                mConditionTextView.setText("*WEBSITE ready to push to FIREBASE!*");
                //reset website text field
                mEditText3.setText("");


                //mConditionRef2.setValue(webInput.getText());
                //web= webInput.getTransitionName();
            }
        });


        //*******ERROR CHECK END******


        mCondition4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextView textBarNotify= (TextView) findViewById(textView);
                //mConditionTextView.setText("***RESETED DATA IN FIREBASE***");
                name="*RESET*";
                pass="*RESET*";
                web="*RESET*";

                mConditionTextView.setText("*RESETTED INFO BEFORE PUSHED TO FIREBASE!*.");


             /***NEEDED IF NODES ON DATABASE mConditionRefX is being changed***
                mConditionRef.setValue("*RESET*");
                mConditionRef1.setValue("*RESET*");
                mConditionRef2.setValue("*RESET*");
                */

             /*   //***SET USERNAME****
                EditText userNameInput= (EditText) findViewById(editText);
                mConditionRef.setValue(userNameInput.getText());
                //***SET PASSWORD***
                EditText passwordInput= (EditText) findViewById(editText2);
                mConditionRef1.setValue(passwordInput.getText());
                //***SET WEBSITE***
                EditText webInput=(EditText) findViewById(editText3);
                mConditionRef2.setValue(webInput.getText()); */
               // mConditionRef1.setValue("***RESETTED***");

            }
        });
        //***PUSH FUNCTION TO ADD CHILD NODES INTO DATABASE***
        mCondition5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              //  mConditionRef0.setValue(new User("alanisawesome", "The Turing Machine"));
              //  mPostRef.setValue(new User("alanisawesome", "The Turing Machine"));
                if(name!="*RESET*"&& pass!="*RESET*"&& web!="*RESET*") {


                    mPostRef.push().setValue(new User(name, pass, web));
                    mPostRef1.push().setValue(new Websites(web));
                    mPostRef2.push().setValue(new Users(name));

                    //hmap.put(countHmap, new User(name,pass,web));
                    //countHmap++; //increment key
                    //System.out.print(hmap.get(countHmap));

                   //mConditionTextView.setText();
                   // mConditionTextView.setText(hmap.get(countHmap).Password+" "+hmap.get(countHmap).Username+" "+hmap.get(countHmap).Website); //displays data pushed onto hmap


                    // mPostRef1.push().setValue(new Websites(web)); //push web data onto a separate node
                   // mPostRef2.push().setValue(new Users(name));  //push user data onto a separate node

                    mConditionTextView.setText("*INFORMATION SENT*");
                    //reset data containers on app
                    name="*RESET*";
                    pass="*RESET*";
                    web="*RESET*";


                    //***RESET TEXT INPUT FIELD VIEW IN APP***
                    mEditText.setText(" ");
                    mEditText2.setText(" ");
                    mEditText3.setText(" ");

                }
                else
                {
                   // System.out.println("Please enter values for all of the textfields.");
                    mConditionTextView.setText("*Invalid Information,NOT SENT!: Please enter valid info into all fields before 'Push'*.");
                }
              //  newPostRef.setValue(new User("gracehop", "Announcing COBOL, a New Programming Language"));


            }
        });




    }
        //***ERROR WHEN TRING TO RUN BUTTON 3***
      /*  mCondition3.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick( View v){
                mConditionRef2.setValue("***www.website.com***");

            }
        });*/


        //***ERROR WHEN TRING TO RUN BUTTON 3***
       /*mCondition3.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick( View v){
                mConditionRef2.setValue("www.website.com");

            }
        });*/





        protected void onStart(){
        super.onStart();





    }
}
