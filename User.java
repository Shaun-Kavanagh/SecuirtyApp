package com.example.shaun.securityapp;

import javax.crypto.SecretKey;

/**
 * Created by Zach on 17/11/2016.
 */
public class User {

    public String Username;
    public String Password;
    public String Website;
    public SecretKey secretKey;

    //Default Constructor

   public User(String Username, String Password) {
        // ...
        this.Username=Username;
        this.Password=Password;

    }

     public User(String Username, String Password, String Website, SecretKey secretKey) {
            // ...
                this.Username=Username;
                this.Password=Password;
                this.Website=Website;
                this.secretKey=secretKey;

        }




}
