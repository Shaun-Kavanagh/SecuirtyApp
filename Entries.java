package com.example.dave.test;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;


/**
 * Created by shaun on 14/10/2016.
 */

public class Entries extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entries);
        mAuth = FirebaseAuth.getInstance();

        final Button myButton = (Button)findViewById(R.id.Gen);
        final Button entry1=(Button)findViewById(R.id.entry1);
        final String num;
        final Button entry2=(Button)findViewById(R.id.entry2);
        final Button entry3=(Button)findViewById(R.id.entry3);
        final Button entry4=(Button)findViewById(R.id.entry4);
        final Button signOut = (Button) findViewById(R.id.buttonSignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(Entries.this, MainActivity.class));


            }
        });
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String temp =user.getUid();
        final Context context=this.getApplicationContext();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        for(int i=0;i<4;i++) {
            String numID = "Blank Entry";
            if (i == 0)
                numID = "one";
            else if (i == 1)
                numID = "two";
            else if (i == 2)
                numID = "three";
            else if (i == 3)
                numID = "four";
            DatabaseReference ref = database.getReference("/Entries/" + temp + "/" + numID);
            if (i == 0) {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        bringback info = dataSnapshot.getValue(bringback.class);
                        entry1.setText(info.getWebsite());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // ...
                    }
                });

            }
            else  if (i == 1) {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        bringback info = dataSnapshot.getValue(bringback.class);
                        entry2.setText(info.getWebsite());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // ...
                    }
                });

            }
            else  if (i == 2) {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        bringback info = dataSnapshot.getValue(bringback.class);
                        entry3.setText(info.getWebsite());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // ...
                    }
                });

            }
            else  if (i == 3) {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        bringback info = dataSnapshot.getValue(bringback.class);
                        entry4.setText(info.getWebsite());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // ...
                    }
                });

            }
        }


        entry1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=entry1.getText().toString();
                if(!text.equals("Enter Site Name ")) {
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                    // extras.putString("filename",filename);
                    extras.putString("NumID","one");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                    //  extras.putString("filename",filename);
                    extras.putString("NumID","one");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }
            }
        });

        entry2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=entry2.getText().toString();
                if(!text.equals("Enter Site Name ")) {
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                   // extras.putString("filename",filename);
                    extras.putString("NumID","two");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                  //  extras.putString("filename",filename);
                    extras.putString("NumID","two");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }
            }
        });
        //Button 3 and file 3
        entry3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=entry3.getText().toString();
                if(!text.equals("Enter Site Name ")) {
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                    // extras.putString("filename",filename);
                    extras.putString("NumID","three");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                    //  extras.putString("filename",filename);
                    extras.putString("NumID","three");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }
            }
        });
        entry4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=entry4.getText().toString();
                if(!text.equals("Enter Site Name ")) {
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                    // extras.putString("filename",filename);
                    extras.putString("NumID","four");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                    //  extras.putString("filename",filename);
                    extras.putString("NumID","four");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }
            }
        });
        myButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ButtonIntent= new Intent(Entries.this, Generation.class);
                startActivity(ButtonIntent);
            }
        });
        final Button myButton2 = (Button)findViewById(R.id.Check);
        myButton2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ButtonIntent= new Intent(Entries.this, Checker.class);
                startActivity(ButtonIntent);
            }
        });
    }

}