package com.example.dave.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //this override class has to be present
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_main);
        //this initalizes the interactable objects from the xml sheet named actitiy_main
        //EditText lets a user input data, basically write
        final EditText userName=(EditText) findViewById(R.id.editTextUserName);
        final EditText Password=(EditText) findViewById(R.id.editTextPassword);
        //Buttons are buttons that you click
        final Button Login=(Button) findViewById(R.id.buttonLogin);
        final Button SignUp=(Button)findViewById(R.id.buttonSignUp);

        //this is an onClickListener, basicaly it carries out the desired action when ever the button is pressed by the user
        //an inent is used, this intent basically calls the program to enter another activity
        //in this case we would have the intent linking to a the entries page of our application
        Login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ButtonIntent= new Intent(MainActivity.this,  Entries.class);
                startActivity(ButtonIntent);
            }
        });
        SignUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ButtonIntent= new Intent(MainActivity.this,  SignUp.class);
                startActivity(ButtonIntent);
            }
        });
    }
}