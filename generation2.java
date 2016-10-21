//Don't take this one either please.
package test;

import edu.vt.middleware.password.*;
import java.util.*;
public class generation2 
{
	public static void main(String args[])
	{
		PasswordGenerator generator = new PasswordGenerator();
		List<CharacterRule> rules = new ArrayList<CharacterRule>();
		rules.add(new DigitCharacterRule(1));
		rules.add(new NonAlphanumericCharacterRule(1));
		rules.add(new UppercaseCharacterRule(1));
		rules.add(new LowercaseCharacterRule(1));
	
		String password = generator.generatePassword(32, rules);
		System.out.print(password);
	}
}
