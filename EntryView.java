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

        //newscanContent=getIntent().getStringExtra("scanContent").trim();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String temp =user.getUid();
        System.out.println(user.getUid());

        Button Edit = (Button) findViewById(R.id.buttonEdit);
        Intent intent = getIntent();
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
                bringback info=dataSnapshot.getValue(bringback.class);
                siteName.setText(info.getWebsite());
                username.setText(info.getUsername());
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


        DatabaseReference databaseReferenceName = (FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("UserEntriesInfo"));
        Firebase ref1= new Firebase("https://console.firebase.google.com/project/securityapp-b04b9/database/data");
       /* databaseReferenceName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              //  System.out.println(dataSnapshot.getValue());
                //String text = dataSnapshot.getValue().toString();
                //username.setText(text);
                System.out.println("There are " + dataSnapshot.getChildrenCount()+" posts");
                    bringback post=postSnapshot.getValue(bringback.class);
                    String user1=post.getUsername();
                    username.setText(user1);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/
      /* com.firebase.client.Query queryRef=ref1.orderByChild("Username");
        queryRef.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String previousChild) {
                bringback facts = dataSnapshot.getValue(bringback.class);
                System.out.println(dataSnapshot.getKey() + "was" + facts.getUsername());
            }
            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });*/



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