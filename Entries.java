package com.example.shaun.securityapp;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateTimePatternGenerator;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.LinearLayout.LayoutParams;
import com.google.firebase.auth.FirebaseAuth;

import static android.R.interpolator.linear;


/**
 * Created by shaun on 14/10/2016.
 */

public class Entries extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

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

        final Context context=this.getApplicationContext();

        //Button 1 and file 1
        String filename1="CharacterProfile1";
        FileIO File= new FileIO();
        String CharacterName1 =File.load(filename1,context);
        if(CharacterName1.equals("cant read file")){
            //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
            CharacterName1="Blank Entry";
        }
        entry1.setText(CharacterName1);
        entry1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename ="CharacterProfile1";
                String id="one";
                FileIO File=new FileIO();
                String text =File.load(filename,context);
                if(text.equals("cant read file")){
                    //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
                    text="Blank Entry";
                }
                if(!text.equals("Blank Entry")) {
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                    extras.putString("filename",filename);
                    extras.putString("NumID","one");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                    extras.putString("filename",filename);
                    extras.putString("NumID","one");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }
            }
        });
        //Button 2 and file 2
        String filename2="CharacterProfile2";
        String CharacterName2 =File.load(filename2,context);
        if(CharacterName2.equals("cant read file")){
            //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
            CharacterName2="Blank Entry";
        }
        entry2.setText(CharacterName2);
        entry2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename ="CharacterProfile2";
                FileIO File=new FileIO();
                String text =File.load(filename,context);
                if(text.equals("cant read file")){
                    //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
                    text="Blank Entry";
                }
                if(!text.equals("Blank Entry")) {
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                    extras.putString("filename",filename);
                    extras.putString("NumID","two");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                    extras.putString("filename",filename);
                    extras.putString("NumID","two");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }
            }
        });
        //Button 3 and file 3
        String filename3="CharacterProfile3";
        String CharacterName3 =File.load(filename3,context);
        if(CharacterName3.equals("cant read file")){
            //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
            CharacterName3="Blank Entry";
        }
        entry3.setText(CharacterName3);
        entry3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename ="CharacterProfile3";
                FileIO File=new FileIO();
                String text =File.load(filename,context);
                if(text.equals("cant read file")){
                    //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
                    text="Blank Entry";
                }
                if(!text.equals("Blank Entry")) {
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                    extras.putString("filename",filename);
                    extras.putString("NumID","three");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                    extras.putString("filename",filename);
                    extras.putString("NumID","three");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }
            }
        });
        //Button 4 and file 4
        String filename4="CharacterProfile4";
        String CharacterName4 =File.load(filename4,context);
        if(CharacterName4.equals("cant read file")){
            //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
            CharacterName4="Blank Entry";
        }
        entry4.setText(CharacterName4);
        entry4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename ="CharacterProfile4";
                FileIO File=new FileIO();
                String text =File.load(filename,context);
                if(text.equals("cant read file")){
                    //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
                    text="Blank Entry";
                }
                if(!text.equals("Blank Entry")) {
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class);
                    Bundle extras = new Bundle();
                    extras.putString("filename",filename);
                    extras.putString("NumID","four");
                    ButtonIntent.putExtras(extras);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class);
                    Bundle extras = new Bundle();
                    extras.putString("filename",filename);
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