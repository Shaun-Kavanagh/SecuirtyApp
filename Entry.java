package com.example.dave.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Dave on 26/10/2016.
 */
public class Entry extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entry);
        final TextView showPass=(TextView) findViewById(R.id.textViewGen);
        final Button generate=(Button)findViewById(R.id.Generate);



        generate.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //init obj and call
                obj pass= new obj();
                String password="";
                password=pass.Generate(15, true, true, true, true);
                showPass.setText(password);
            }
        });

    }
}
