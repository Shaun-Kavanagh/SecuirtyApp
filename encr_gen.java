package com.example.shaun.securityapp;

/**
 * Created by shaun on 21/10/2016.
 */

import javax.crypto.*;


import edu.vt.middleware.password.*;

import java.util.*;

    public class encr_gen {
        public static void main(String args[]) throws Exception
        {


            KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
            //create a 128byte key generator
            KeyGen.init(128);
            //this is the key
            SecretKey SecKey = KeyGen.generateKey();
            //shows that you are using the AES encr
            Cipher AesCipher = Cipher.getInstance("AES");

            //with second generator
            //add asset manager to manage file IO
            String Filename3 = "encrypted1.txt";
            String Filename4 = "decrypted1.txt";

            PasswordGenerator generator = new PasswordGenerator();
            //this adds rules to the the gen
            List<CharacterRule> rules = new ArrayList<CharacterRule>();
            rules.add(new DigitCharacterRule(1));
            rules.add(new NonAlphanumericCharacterRule(1));
            rules.add(new UppercaseCharacterRule(1));
            rules.add(new LowercaseCharacterRule(1));
            //create variable to take in length of password, right now its 20.
            String password = generator.generatePassword(20, rules);
            System.out.println("Second Generator Password: " + password);
            //converts the password from string to byte [], this is necessary for AES encr
            byte[] byteText1 = password.getBytes();
            //enters encrypt mode, uses the secret key to encrypt and decrypt the files
            AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
            //this then encrypts the file
            byte[] byteCipherText1 = AesCipher.doFinal(byteText1);
            //this is where we need to send off the data to out database.
            //ALSO WE NEED TO DISPLAY THE PASSWORD BACK TO THE USER
            //TEXTVIEW .setText will be used here.
            //we should show the password before we send it to the database.
            //Files.write(Paths.get(Filename3), byteCipherText1);

           // byte[] cipherText1 = Files.readAllBytes(Paths.get(Filename3));

          //  AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
            //byte[] bytePlainText1 = AesCipher.doFinal(cipherText1);
           // Files.write(Paths.get(Filename4), bytePlainText1);

        }
    }

