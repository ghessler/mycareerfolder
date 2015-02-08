package codingexercise.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.glassfish.hk2.api.Self;

import com.fasterxml.jackson.annotation.JsonProperty;
 
public class PasswordValidator{
 
	  private Pattern pattern;
	  private Pattern repeatPattern;
	  private Matcher matcher;
	  private Matcher repeatMatcher;
	  private boolean valid;
 
	  /**
	   * Constructor validates password with regular expression
	   * @param regex regex for validation
	   * @param password password for validation
	   */
	  public PasswordValidator(final String regex, final String password){
		  pattern = Pattern.compile(regex);
		  repeatPattern = Pattern.compile("([a-z0-9]{2,})\\1$");
		  matcher = pattern.matcher(password);
		  repeatMatcher = repeatPattern.matcher(password);
		  valid = matcher.matches() && !repeatMatcher.matches();
	  }
 
	  /**
	   * Getter for the password validity
	   * @return true valid password, false invalid password
	   */
	  @JsonProperty
	  public boolean getValid(){
		  System.out.println("The password validity is " + valid);
		  return valid;
	  }
}