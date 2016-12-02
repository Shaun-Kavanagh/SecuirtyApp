package com.example.shaun.securityapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText userName;
    private EditText Password;
    //this override class has to be present
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //this initalizes the interactable objects from the xml sheet named actitiy_main
        //EditText lets a user input data, basically write
        userName=(EditText) findViewById(R.id.editTextUserName);
        Password=(EditText) findViewById(R.id.editTextPassword);
        //Buttons are buttons that you click
        final Button Login=(Button) findViewById(R.id.buttonLogin);
        final Button SignUp=(Button)findViewById(R.id.buttonSignUp);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){

                    startActivity(new Intent(MainActivity.this, Entries.class));

                }
            }
        };
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSignIn();

            }
        });

        //this is an onClickListener, basicaly it carries out the desired action when ever the button is pressed by the user
        //an inent is used, this intent basically calls the program to enter another activity
        //in this case we would have the intent linking to a the entries page of our application
       /* Login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ButtonIntent= new Intent(MainActivity.this,  Entries.class);
                startActivity(ButtonIntent);
            }
        });*/
        SignUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ButtonIntent= new Intent(MainActivity.this,  SignUp.class);
                startActivity(ButtonIntent);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener((mAuthListener));
    } private void startSignIn(){

        String email = userName.getText().toString();
        String password = Password.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(MainActivity.this, "Fields are Empty", Toast.LENGTH_LONG).show();

        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}