package com.example.shaun.securityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.firebase.client.Firebase;

/**
 * Created by Dave on 11/11/2016.
 */
public class EntryView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TextView Name;
        super.onCreate(savedInstanceState);
        //set which activity is being called i.e the xml file
        setContentView(R.layout.activity_entry_view);

        //databaseReference= Firebase.getInstance().getReference();
        //newscanContent=getIntent().getStringExtra("scanContent").trim();
        //firebaseAuth=FebaseAuth.getInstance();

        Button Edit= (Button) findViewById(R.id.buttonEdit);
        Intent intent = getIntent();
        final String Profile = intent.getStringExtra("filename");
        TextView siteName= (TextView)findViewById(R.id.siteName);
        TextView username= (TextView)findViewById(R.id.username);
        TextView password=(TextView)findViewById(R.id.password);
        Edit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ButtonIntent = new Intent(EntryView.this, Entry.class).putExtra("filename",Profile);
                startActivity(ButtonIntent);

            }
        });
    }
}
