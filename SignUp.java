package com.example.shaun.securityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by shaun on 16/10/2016.
 *
 * This class needs to send take in user input and write to the database
 */
public class SignUp extends AppCompatActivity {
    String username="";
    String password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_signup);
        final EditText userName=(EditText) findViewById(R.id.editTextUserName);
        final EditText Password=(EditText) findViewById(R.id.editTextPassword);
        //Buttons are buttons that you click
        final Button SignUp=(Button) findViewById(R.id.buttonSignUp);


        SignUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take in data
                username=userName.getText().toString();
                password=Password.getText().toString();
                ///now you would call the method to check how strong the password is and if the username is taken
                //then you would connect to the database and send the username and password.

            }
        });



    }

}
