package com.example.dave.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaun on 26/10/2016.
 */
public class Entry extends AppCompatActivity  {
    String name,pass,web;
    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mPostRef=mRootRef.child("UserEntriesInfo"); //to push data using this node path
    DatabaseReference mConditionRef=mRootRef.child("username");
    DatabaseReference mConditionRef1=mRootRef.child("password");
    DatabaseReference mConditionRef2=mRootRef.child("website_entry");
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("/Entries");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);


        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entry);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //  final String Profile =  extras.getString("filename");
        final String numID=extras.getString("NumID");
        final EditText passWord=(EditText)findViewById(R.id.password);
        final EditText userName=(EditText)findViewById(R.id.username);
        final EditText siteName=(EditText)findViewById(R.id.siteName);
        final Button firebaseclass= (Button)findViewById(R.id.firebaseTest);
        final Context context = this.getApplicationContext();
        String temp =user.getUid();
        final DatabaseReference ref = database.getReference("/Entries");
      /*  ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bringback info = dataSnapshot.getValue(bringback.class);
                siteName.setText(info.getWebsite());
                userName.setText(info.getUsername());
                obj enc = new obj();
                String sec = info.getSecKey();
                byte[] sec1 = null;
                try {
                    sec1=sec.getBytes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SecretKey originalKey = null;
                try {
                    originalKey = new SecretKeySpec(sec1, 0, sec1.length, "AES");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String pass = info.getPassword();
                try {
                    pass = enc.Decrypt(pass, originalKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                passWord.setText(pass);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        firebaseclass.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass=passWord.getText().toString();

                DatabaseReference usersRef = ref.child(user.getUid().toString());

                Map<String, User> users = new HashMap<String, User>();
                //siteName.getText().toString()
                //usersRef.child(numID).setValue(new User(userName.getText().toString(),passWord.getText().toString(),siteName.getText().toString()))
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
                byte[] sec1=SecKey.getEncoded();
                String sec = new String(sec1);

                usersRef.child(numID).setValue(new User(userName.getText().toString(),pass,siteName.getText().toString() ));

                name=userName.getText().toString();
                //mConditionRef.setValue(userNameInput.getText());
                // mConditionTextView.setText("*USERNAME ready to push to FIREBASE!*.");
                //reset username text field
                userName.setText("");

                passWord.setText("");
                web=siteName.getText().toString();
             //   String Name = siteName.getText().toString()+ "\n";
                siteName.setText("");


                Intent ButtonIntent = new Intent(Entry.this, Entries.class);
                startActivity(ButtonIntent);
            }
        });

    }
}