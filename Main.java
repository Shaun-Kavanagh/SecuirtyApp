package classes;
import javax.crypto.*;
import java.util.*;
/*	
 *	This main is only used for testing the object and see how it behaves with different calls
 *	I'm using booleans for what the basic user could want to do
 *	For the password checker we could use something like tick boxes for each rule, which could give us booleans and make it easy to code and understand
 *	Current errors include user misspelling of true/false in scanner, which will be fixed by tick boxes
 *	Also currently the password checker does not check against a dictionary of common passwords, I will attempt to implement this feature soon.
 */
public class Main 
{
	public static void main(String args[]) throws Exception
	{
		//Creates new object with all the functionality
		obj test = new obj();
		
		//Declare Integer and Booleans used later in code, can be cleaned up but currently unnecessary
		int length;
		boolean Digit, Special, Upper, Lower, Check, Pass;
		
		//Ask if user wants to create his own password
		System.out.println("Do you wish to create your own password? Answer True if Yes" + "\n" + "if not we will generate a password with your needs");
		
	    //Takes in the input from user, if true user creates his own pass, else program generates one
		Scanner sc = new Scanner(System.in);
		Pass = sc.nextBoolean();
		
		//As .nextBoolean() Does not skip a line, the reader would have problems reading the string password input(doesn't wait for user input)
		sc.nextLine();
		
		//Declare Password String
		String password;
		
		//Ask user for Password and take it in into variable
		if(Pass)
		{
			System.out.println("Enter your Password");
			password = sc.nextLine();
		}
		else
		{
			//Takes in booleans to select rules for the password generation that user wishes for
			System.out.println("Enter Password length");
			length = sc.nextInt();
			System.out.println("Enter true or false for the following, min of 1 has to be selected");
			System.out.println("Do you wish to have Digits?");
			Digit = sc.nextBoolean();
			System.out.println("Do you wish to have Special Characters?");
			Special = sc.nextBoolean();
			System.out.println("Do you wish to have Uppercase Letters?");
			Upper = sc.nextBoolean();
			System.out.println("Do you wish to have Lowercase Letters?");
			Lower = sc.nextBoolean();
			
			
			//Generates password of given length according to given rules
			password = test.Generate(length, Digit, Special, Upper, Lower);
			
			//Prints Password for testing purpouses
			System.out.println("Your Password: " + password);
		}
		
		//Generates Secret Key which will be used to Encrypt and Decrypt The password
		SecretKey SecKey = test.GenerateSecKey();
		
		//Creates a byte array in which the Ecrypted password is stored in a byte array which is what Aes encryption works with
		byte[] EncryptedPass = test.Encrypt(password, SecKey);
		
		//Prints out the Encrypted Byte Array
		System.out.println("Encrypted Password Shown with Bytes " + EncryptedPass);
		
		//Encrypted Password readable to humans(converted from bytecode to ASCII
		String pass = new String(EncryptedPass);
		System.out.println("Encypted Password Shown: " + pass);
		
		//Shows Decrypted Password for Testing Purpouses
		System.out.println("Decrypted Password: " + test.Decrypt(EncryptedPass, SecKey));
		
		//Ask user if he wishes to check his password against a rule book
		System.out.println("Do you wish to check your Password?");
		Check = sc.nextBoolean();
		
		//if true the runs the checker on password
		if(Check)test.Checker(password);
		
		//Closes Scanner so no Memory Leaks Occur
		sc.close();
		
		
	}

}
