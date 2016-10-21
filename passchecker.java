package test;
import edu.vt.middleware.password.*;
import java.util.*;
public class passchecker 
{
	public static void main(String args[])
	{
		LengthRule lengthRule = new LengthRule(8, 26);
		WhitespaceRule whitespaceRule = new WhitespaceRule();
	
		AlphabeticalSequenceRule alphaSeqRule = new AlphabeticalSequenceRule();
		RepeatCharacterRegexRule repeatRule = new RepeatCharacterRegexRule(4);
		
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(lengthRule);
		ruleList.add(whitespaceRule);
		ruleList.add(alphaSeqRule);
		ruleList.add(repeatRule);
		ruleList.add(new LowercaseCharacterRule(1));
		ruleList.add(new DigitCharacterRule(1));
		ruleList.add(new NonAlphanumericCharacterRule(1));
		ruleList.add(new UppercaseCharacterRule(1));
		
		PasswordValidator validator = new PasswordValidator(ruleList);
		
		PasswordData passwordData = new PasswordData(new Password("4444444"));
		
		RuleResult result = validator.validate(passwordData);
				
		if (result.isValid())
			  System.out.println("Valid password");
		else
		{
			  System.out.println("Invalid password:");
			  for (String msg : validator.getMessages(result))
			  {
				    System.out.println(msg);
			  }
		}
	}

}
