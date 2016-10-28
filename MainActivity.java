package com.example.zach.myapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.*;

import static com.example.zach.myapp2.R.id.textView;


public class MainActivity extends AppCompatActivity {
    TextView mConditionTextView;
    Button mCondition;
    Button mCondition2;

    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef=mRootRef.child("condition");


    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        //Get UI elements
        mConditionTextView = (TextView) findViewById(textView);
        final Button mCondition = (Button) findViewById(R.id.condition1);
        final Button  mCondition2 = (Button) findViewById(R.id.condition2);


         }

        protected void onStart(){
        super.onStart();


            final ValueEventListener valueEventListener = mConditionRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    mConditionTextView.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mCondition.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick (View v){
                    mConditionRef.setValue("***USERNAME***");

                }
            });

            mCondition2.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick( View v){
                    mConditionRef.setValue("***PASSWORD***");

                }
            });


    }
}
