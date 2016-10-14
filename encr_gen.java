package test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.crypto.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;

import edu.vt.middleware.password.*;

import java.util.*;

public class encr_gen 
{
	public static void main(String args[]) throws Exception
	{
		//With first generator
		String FileName = "encrypted.txt";
		String FileName2 = "decrypted.txt";
		
		String test = RandomStringUtils.random(20, true, true);
		System.out.println("Password being tested: " + test);
		
		KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
		KeyGen.init(128);

		SecretKey SecKey = KeyGen.generateKey();

		Cipher AesCipher = Cipher.getInstance("AES");
		
        byte[] byteText = test.getBytes();
        
        AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
        byte[] byteCipherText = AesCipher.doFinal(byteText);
        Files.write(Paths.get(FileName), byteCipherText);

        byte[] cipherText = Files.readAllBytes(Paths.get(FileName));

        AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
        byte[] bytePlainText = AesCipher.doFinal(cipherText);
        Files.write(Paths.get(FileName2), bytePlainText);
        
        //with second generator
        String Filename3 = "encrypted1.txt";
        String Filename4 = "decrypted1.txt";
        
        PasswordGenerator generator = new PasswordGenerator();
		List<CharacterRule> rules = new ArrayList<CharacterRule>();
		rules.add(new DigitCharacterRule(1));
		rules.add(new NonAlphanumericCharacterRule(1));
		rules.add(new UppercaseCharacterRule(1));
		rules.add(new LowercaseCharacterRule(1));
		
		String password = generator.generatePassword(20, rules);
		System.out.println("Second Generator Password: " + password);
		byte[] byteText1 = password.getBytes();
		
		AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
        byte[] byteCipherText1 = AesCipher.doFinal(byteText1);
        Files.write(Paths.get(Filename3), byteCipherText1);

        byte[] cipherText1 = Files.readAllBytes(Paths.get(Filename3));

        AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
        byte[] bytePlainText1 = AesCipher.doFinal(cipherText1);
        Files.write(Paths.get(Filename4), bytePlainText1);
		
	}
}
