package com.example.dave.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * Created by shaun on 26/10/2016.
 */
public class Entry extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entry);

        final Button send=(Button)findViewById(R.id.send);

        final EditText password=(EditText)findViewById(R.id.password);
        final EditText siteName=(EditText)findViewById(R.id.siteName);



        send.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String pass=password.getText().toString();
                String name=siteName.getText().toString();
                //send this to firebase
                //create objects
                obj enc=new obj();
                SecretKey SecKey = null;
                //Generation of Secret key, In try catch to get rid of exceptions
                try {
                    SecKey = enc.GenerateSecKey();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String password = enc.Encrypt(pass, SecKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //this is where the "password" is an encrypted string, this is going to be sent to firebase
                //along with the username tied to it
                //This code hasn't been stress tested yet
                //Also this doesn't create an entry yet, just for future reference 



            }
        });

    }
}