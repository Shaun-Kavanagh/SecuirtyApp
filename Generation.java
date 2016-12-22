package com.example.dave.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;

/**
 * Created by Dave on 09/11/2016.
 */
public class Generation extends AppCompatActivity
{
    private int length = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setcontentView sets what xml file is being used here
        setContentView(R.layout.activity_generation);
        //inti all the xml resources that are used
        final EditText showPass= (EditText) findViewById(R.id.Password);
        final Button generate=(Button)findViewById(R.id.Gen);
        final TextView Label =(TextView)findViewById(R.id.LengthT);
        final EditText inPass=(EditText)findViewById(R.id.Length);
        final CheckBox Digit = (CheckBox)findViewById(R.id.Digits);
        final CheckBox Special = (CheckBox)findViewById(R.id.Special);
        final CheckBox Upper = (CheckBox)findViewById(R.id.Uppercase);
        final CheckBox Lower = (CheckBox)findViewById(R.id.Lowercase);

        generate.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //init obj call
                String text= inPass.getText().toString();
                //handle the length
                try {
                    length = Integer.parseInt(text);
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                if(length < 4)
                {
                    length = 8;
                }

                obj pass= new obj();
                //call the generation methods
                String Password =pass.Generate(length,Digit.isChecked(),Special.isChecked(),Upper.isChecked(),Lower.isChecked());
                //display the resulting string
                showPass.setText(Password);
            }
        });
    }
}
