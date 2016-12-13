package com.example.shaun.securityapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

/**
 * Created by shaun on 16/10/2016.
 *
 * This class needs to send take in user input and write to the database
 */
public class SignUp extends AppCompatActivity {
    private static final String TAG = "Signup";

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button msignupBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Button mlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = (EditText) findViewById(R.id.editTextUserName);
        mPasswordField = (EditText) findViewById(R.id.editTextPassword);
        msignupBtn = (Button) findViewById(R.id.buttonLogin);
          FirebaseUser user = mAuth.getCurrentUser();
                 //   System.out.println(user.getUid());

        mlogin = (Button) findViewById(R.id.buttonBack);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        msignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailField.getText().toString().trim();
                String password = mPasswordField.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    FirebaseUser user1 = firebaseAuth.getCurrentUser();
                                    if(user1!=null) {
                                        String temp = user1.getUid();
                                        System.out.println(temp);
                                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        //DatabaseReference ref = database.getReference("/Entries");

                                        final DatabaseReference usersRef = database.getReference("/Entries/" + temp);
                                        for (int i = 0; i < 4; i++) {

                                            if (i == 0) {
                                                usersRef.child("one").setValue(new User("Username", "Enter Password", "Enter Site Name "));

                                            } else if (i == 1) {
                                                usersRef.child("two").setValue(new User("Username", "Enter Password", "Enter Site Name "));

                                            } else if (i == 2) {
                                                usersRef.child("three").setValue(new User("Username", "Enter Password", "Enter Site Name "));
                                            } else if (i == 3) {
                                                usersRef.child("four").setValue(new User("Username", "Enter Password", "Enter Site Name "));
                                            }
                                        }
                                        startActivity(new Intent(SignUp.this, Entries.class));
                                        finish();
                                    }else {
                                        startActivity(new Intent(SignUp.this, Entries.class));
                                        finish();
                                    }
                                }
                            }
                        });


            }
        });
        mlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ButtonIntent= new Intent(SignUp.this,  MainActivity.class);
                startActivity(ButtonIntent);

            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}