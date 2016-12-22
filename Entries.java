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
    //authorisation variavles used in this method
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entries);
        //get reference to the particular database
        mAuth = FirebaseAuth.getInstance();
        //initalise all the buttons from the xml file
        final Button myButton = (Button)findViewById(R.id.Gen);
        final Button entry1=(Button)findViewById(R.id.entry1);
        final String num;
        final Button entry2=(Button)findViewById(R.id.entry2);
        final Button entry3=(Button)findViewById(R.id.entry3);
        final Button entry4=(Button)findViewById(R.id.entry4);
        final Button signOut = (Button) findViewById(R.id.buttonSignOut);
        //if the user wants to sign out
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //signs the user out of the current Uid they are in
                FirebaseAuth.getInstance().signOut();

                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(Entries.this, MainActivity.class));


            }
        });
        //get the reference to the current user so the User ID can be obtained
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String temp =user.getUid();
        final Context context=this.getApplicationContext();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //this for loop sets the names of the four entry buttons by connecting to the DB
        //it does this by changing the NumID each iteration of the loop, this NumId is then handled in an if statment
        //the if statment matches the corresponding string to the iteration
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
            //the numID string is then concatenated to the reference to the database so the the reference has the correct path to the
            //right entry.
            DatabaseReference ref = database.getReference("/Entries/" + temp + "/" + numID);
            if (i == 0) {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //bringback is a method that gets a snapshot of the data
                        bringback info = dataSnapshot.getValue(bringback.class);
                        //we can then access the website name that the user put in and set it to the text on each button
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
                        try {
                            entry4.setText(info.getWebsite());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // ...
                    }
                });

            }
        }

        //this is an onclicklistener for when the user wants to access a particular entry.
        entry1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String text=entry1.getText().toString();
                //checks that the user has made an entry before
                if(!text.equals("Enter Site Name ")) {
                    //sends the user to the entryView activity while also sending the NumID so the EntryView knows which button on the database to access
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                    // extras.putString("filename",filename);
                    extras.putString("NumID","one");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    //if the user hasn't made an entry before this sends them to the entry creation activity
                    //it also sends the NumID
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                    //  extras.putString("filename",filename);
                    extras.putString("NumID","one");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }
            }
        });
        //all the entry on clicklisteners do the same thing
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
        //this sends the user to the generation activity
        myButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ButtonIntent= new Intent(Entries.this, Generation.class);
                startActivity(ButtonIntent);
            }
        });
        //this sends the user to the checker activity
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
