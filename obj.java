package classes;
import javax.crypto.*;
import edu.vt.middleware.password.*;
import java.util.*;
public class obj
{
	//Creates string to be used in the object
	public String password;
	
	//Generates Password according to given length and rules read in as booleans, more rules can be added later
	public String Generate(int length, boolean Digit, boolean Special, boolean Upper, boolean Lower)
	{
		PasswordGenerator generator = new PasswordGenerator();
		List<CharacterRule> rules = new ArrayList<CharacterRule>();
		if(Digit)rules.add(new DigitCharacterRule(1));
		if(Special)rules.add(new NonAlphanumericCharacterRule(1));
		if(Upper)rules.add(new UppercaseCharacterRule(1));
		if(Lower)rules.add(new LowercaseCharacterRule(1));
	
		String password = generator.generatePassword(length, rules);
		return password;
	}
	
	//Generates Secret Key, this has to be made separately as encryption and decryption requires same key to work
	//This is easiest to achieve through a separate function
	public SecretKey GenerateSecKey() throws Exception
	{
		//Creates a KeyGenerator for the encryption AES
		//From my understanding most Encryption methods require their own KeyGen
		KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
		
		//Makes all Keys generated to be 128 bytes, can move up to 256 but caused errors for me in code
		KeyGen.init(128);
		
		//Generating Secret Key
		SecretKey SecKey = KeyGen.generateKey();
		return SecKey;
	}
	//Encrypts a given string according to given Secret Key
	//Throws Exception is required for the next functions to run
	public byte[] Encrypt(String password, SecretKey SecKey) throws Exception
	{
		//Creates an AES Cipher
		Cipher AesCipher = Cipher.getInstance("AES");
		
		//As AES works with bytes we have to convert our Password Strings to Byte arrays
		byte[] byteText1 = password.getBytes();
		
		//This Initialises the Encryption mode in AES, using our Secret Key
		AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
		
		//This Encrypts our byte array we produced
        byte[] byteCipherText1 = AesCipher.doFinal(byteText1);
		
        //Returns the ecrypted byte array which will be uploaded to the database
		return byteCipherText1;
	}
	//Decrypts our encrypted byte array using our secret key
	public String Decrypt(byte[] byteCipherText, SecretKey SecKey) throws Exception
	{
		  //Creates an AES Cipher which will be used for decryption
		  Cipher AesCipher = Cipher.getInstance("AES");
		  
		  //Initialises the Cipher in Decrypt mode, with our Secret Key
		  AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
		  
		  //Decrypts our Byte array into a decrypted Byte array
	      byte[] bytePlainText = AesCipher.doFinal(byteCipherText);
	      
	      //We have to convert our byte array back to a string, and a basic toString() will not work as we're working with bytes
	      String Password = new String(bytePlainText);
	      return Password;
	}
	//Used to check the password, will be used to check the strength later
	public void Checker(String password)
	{
		//Creates Rules which are pretty self explanatory 
		//On length the values are inclusive
		LengthRule lengthRule = new LengthRule(8, 26);
		WhitespaceRule whitespaceRule = new WhitespaceRule();		
		AlphabeticalSequenceRule alphaSeqRule = new AlphabeticalSequenceRule();
		RepeatCharacterRegexRule repeatRule = new RepeatCharacterRegexRule(4);
		
		//Creates an Array list to store all the rules
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(lengthRule);
		ruleList.add(whitespaceRule);
		ruleList.add(alphaSeqRule);
		ruleList.add(repeatRule);
		ruleList.add(new LowercaseCharacterRule(1));
		ruleList.add(new DigitCharacterRule(1));
		ruleList.add(new NonAlphanumericCharacterRule(1));
		ruleList.add(new UppercaseCharacterRule(1));
		
		//A validator is what will actually check the password
		PasswordValidator validator = new PasswordValidator(ruleList);
		
		//Converts our password in PasswordData, which is what's gonna be validated
		PasswordData passwordData = new PasswordData(new Password(password));
		
		//RuleResult stores the result of our validation
		RuleResult result = validator.validate(passwordData);
				
		if (result.isValid())
			  System.out.println("Valid password");
		else
		{
			  System.out.println("Invalid password:");
			  
			  //Prints out every error with the password
			  for (String msg : validator.getMessages(result))
			  {
				    System.out.println(msg);
			  }
		}
	}
}
