package com.example.shaun.securityapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by shaun on 26/10/2016.
 */
public class Entry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_entry);
        final TextView showPass= (TextView)findViewById(R.id.textViewGen);
        final Button generate=(Button)findViewById(R.id.Generate);
        final Button Check=(Button)findViewById(R.id.Checker);
        final EditText inPass=(EditText)findViewById(R.id.editTextInput);
        final TextView result=(TextView)findViewById(R.id.textViewShow);


        generate.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                //init obj call
                obj pass= new obj();
                String Password =pass.Generate(15,true,true,true,true);
                showPass.setText(Password);
            }
        });
        Check.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                //init obj call
                String text= inPass.getText().toString();
                obj pass= new obj();
                String Password =pass.Checker(text);
                result.setText(Password);
            }
        });
    }
}
