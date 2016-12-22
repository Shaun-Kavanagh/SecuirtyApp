package com.example.dave.test;

/**
 * Created by Zach on 17/11/2016.
 */
public class User {
//these will be the variables used in the database
    public String Username;
    public String Password;
    public String Website;
  //  public String secretKey;

    //Default Constructor

    public User(String Username, String Password) {
        // ...
        this.Username=Username;
        this.Password=Password;

    }

    public User(String Username, String Password, String Website) {
        //this metho just gives access to set these variables
        // ...
        this.Username=Username;
        this.Password=Password;
        this.Website=Website;
       // this.secretKey=secretKey;

    }




}
