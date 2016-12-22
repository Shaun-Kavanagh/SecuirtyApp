package com.example.dave.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

/**
 * Created by Dave on 11/11/2016.
 */
public class EntryView extends AppCompatActivity {
    // initialise all the variables that are needed to access the database
    Firebase mRef;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReferenceName, databaseReferenceAddress, databaseReferenceNumber, databaseReferenceFacebook, databaseReferenceTwitter, databaseReferenceLinkedin, databaseReferenceGithub;
    private TextView textViewUserID, textViewName, textViewAddress, textViewNumber, textViewFacebook, textViewTwitter, textViewLinkedin, textViewGithub;
    private Button buttonRetrieve;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);

        // TextView Name;
        super.onCreate(savedInstanceState);
        //set which activity is being called i.e the xml file
        setContentView(R.layout.activity_entry_view);

        //get the reference to the current user
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String temp =user.getUid();
        System.out.println(user.getUid());

        Button Edit = (Button) findViewById(R.id.buttonEdit);
        Intent intent = getIntent();
        //get the NumID
        Bundle extras = intent.getExtras();
        // final String Profile =  extras.getString("filename");
        final String numID=extras.getString("NumID");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/Entries/"+temp+"/"+numID);
        final TextView siteName = (TextView) findViewById(R.id.siteName);
        final TextView username = (TextView) findViewById(R.id.username);
        final TextView password = (TextView) findViewById(R.id.password);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //set the particular variables from the database using a snapshot of the data and bringback.java
                bringback info=dataSnapshot.getValue(bringback.class);
                siteName.setText(info.getWebsite());
                username.setText(info.getUsername());
                //this was supposed to be decryption 
               /* obj enc=new obj();
               // String sec=info.getSecKey();
                byte[] sec1 = null;
                try {
                    sec1=sec.getBytes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String pass=info.getPassword();
                SecretKey originalKey = null;
                try {
                    originalKey = new SecretKeySpec(sec1, 0, sec1.length, "AES");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    pass = enc.Decrypt(pass, originalKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                password.setText(info.getPassword());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
        
        //if the user wants to edit the information they see or change it all together this sends them to entry
        //it also sends the NumID so it knows what button it is referencing
        Edit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ButtonIntent = new Intent(EntryView.this, Entry.class);
                Bundle extras = new Bundle();
                // extras.putString("filename",Profile);
                extras.putString("NumID",numID);
                ButtonIntent.putExtras(extras);
                startActivity(ButtonIntent);
                startActivity(ButtonIntent);

            }
        });
    }
}
