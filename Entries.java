package com.example.dave.test;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateTimePatternGenerator;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import static android.R.interpolator.linear;


/**
 * Created by shaun on 14/10/2016.
 */
public class Entries extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entries);

        final Button myButton = (Button)findViewById(R.id.Gen);
        final Button entry=(Button)findViewById(R.id.entry1);

        entry.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent ButtonIntent= new Intent(Entries.this, Entry.class);
                startActivity(ButtonIntent);
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
       /* myButton.setText("Push Me");
        LinearLayout ll = (LinearLayout)findViewById(R.id.buttonLayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll.addView(myButton, lp);
      /*  for (int i = 1; i <= 20; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText("button " + id_);
            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            linear.addView(btn,params);
            final Button  btn1 = ((Button) findViewById(id_));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }*/

    }
}