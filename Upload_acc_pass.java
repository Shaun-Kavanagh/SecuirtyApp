package com.example.zach.myapp2;


/**
 * Created by Zach on 18/10/2016.
 */
public class Upload_acc_pass extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndriodContext(this);
    }
}
