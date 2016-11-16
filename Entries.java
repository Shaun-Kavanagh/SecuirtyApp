package com.example.dave.test;
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

import static android.R.interpolator.linear;


/**
 * Created by shaun on 14/10/2016.
 */

public class Entries extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entries);


        final Button myButton = (Button)findViewById(R.id.Gen);
        final Button entry=(Button)findViewById(R.id.entry1);
        final Context context=this.getApplicationContext();
        String filename1="file1";
        FileIO File= new FileIO();
        String site=File.load(filename1,context);
        if(site.equals("cant read file")){
            //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
            site="Entry Button";
        }else{
            entry.setText(site);
        }
        entry.setText(site);
        entry.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                String filename1="file1";
                FileIO File= new FileIO();
                String site=File.load(filename1,context);
                //entry.setText(site);
                if(site.equals("cant read file")){
                    //this is to handle unknown exception being thrown by the fileIO class when sending in a file that hasn't been created yet
                    site="";
                }
                if(site.length()==0) {
                    Intent ButtonIntent = new Intent(Entries.this, Entry.class).putExtra("filename",site);
                    startActivity(ButtonIntent);
                }else{
                    Intent ButtonIntent = new Intent(Entries.this, EntryView.class).putExtra("filename",site);
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