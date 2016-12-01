package com.example.shaun.securityapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import static com.example.shaun.securityapp.R.id.*;

/**
 * Created by shaun on 26/10/2016.
 */
public class Entry extends AppCompatActivity  {
    String name,pass,web;
    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mPostRef=mRootRef.child("UserEntriesInfo"); //to push data using this node path
    DatabaseReference mPostRef1=mRootRef.child("Websites");
    DatabaseReference mPostRef2=mRootRef.child("User's Names");
    DatabaseReference mConditionRef=mRootRef.child("username");
    DatabaseReference mConditionRef1=mRootRef.child("password");
    DatabaseReference mConditionRef2=mRootRef.child("website_entry");
    DatabaseReference newPostRef = mPostRef.push();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entry);
       /* final ValueEventListener valueEventListener = mConditionRef.addValueEventListener(new ValueEventListener() {

            @Override
            //gets a snapshot of firebase (pulls down data from database)
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                // name=dataSnapshot.getValue(String.class); //to store what has been entered
                mConditionTextView.setText(text);
            }*/
       /* @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });*/

        final Button send=(Button)findViewById(R.id.send);
        Intent intent = getIntent();
        final String Profile = intent.getStringExtra("filename");

        final EditText passWord=(EditText)findViewById(R.id.password);
        final EditText userName=(EditText)findViewById(R.id.username);
        final EditText siteName=(EditText)findViewById(R.id.siteName);
        final Button firebaseclass= (Button)findViewById(R.id.firebaseTest);
        final Context context = this.getApplicationContext();


        firebaseclass.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass=passWord.getText().toString();

                //send this to firebase
                //create objects
                obj enc=new obj();
                SecretKey SecKey = null;
                //Generation of Secret key, In try catch to get rid of exceptions
                try {
                    SecKey = enc.GenerateSecKey();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String password="";
                try {
                     password = enc.Encrypt(pass, SecKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                name=userName.getText().toString();
                //mConditionRef.setValue(userNameInput.getText());
               // mConditionTextView.setText("*USERNAME ready to push to FIREBASE!*.");
                //reset username text field
                userName.setText("");

                passWord.setText("");
                web=siteName.getText().toString();
                String Name = siteName.getText().toString()+ "\n";
                siteName.setText("");

                mPostRef.push().setValue(new User(name, password, web));
                mPostRef1.push().setValue(new Websites(web));
                mPostRef2.push().setValue(new Users(name));

                FileIO File = new FileIO();
                //File.load(Profile, context);

                try {
                    File.save(Profile, Name, context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent ButtonIntent = new Intent(Entry.this, Entries.class);
                startActivity(ButtonIntent);
            }
        });
        send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


                //this is where the "password" is an encrypted string, this is going to be sent to firebase
                //along with the username tied to it
                //This code hasn't been stress tested yet
                //Also this doesn't create an entry yet, just for future reference


    }
}
