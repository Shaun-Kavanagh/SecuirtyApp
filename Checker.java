package com.example.dave.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Dave on 09/11/2016.
 */
public class Checker extends AppCompatActivity
{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //this setcontentView sets what xml file is being used here
            setContentView(R.layout.activity_checker);
            final EditText pass = (EditText) findViewById(R.id.editTextInput);
            final Button check = (Button) findViewById(R.id.Checker);
            final TextView result = (TextView) findViewById(R.id.textViewShow);
            //set an on click listener
            check.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v){
                    //get the password they sent in
                    String text= pass.getText().toString();
                    obj pass= new obj();
                    //call check on the password
                    String Password =pass.Checker(text);
                    //output the result to the textbox
                    result.setText(Password);
                }
            });

        }

}
