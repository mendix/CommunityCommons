// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import communitycommons.StringUtils;

/**
 * Returns a random strong password containing a specified minimum number of digits, uppercase, lowercase and special characters.
 * 
 * Note:Minimumlength should be equal or larger than NrOfCapitalizedCharacters, NrOfLowercaseCharacters, NrOfDigits and NrOfSpecialCharacters
 */
public class RandomStrongPasswordWithLowercase extends CustomJavaAction<java.lang.String>
{
	private java.lang.Long MinLength;
	private java.lang.Long MaxLength;
	private java.lang.Long NrOfCapitalizedCharacters;
	private java.lang.Long NrOfLowercaseCharacters;
	private java.lang.Long NrOfDigits;
	private java.lang.Long NrOfSpecialCharacters;

	public RandomStrongPasswordWithLowercase(IContext context, java.lang.Long MinLength, java.lang.Long MaxLength, java.lang.Long NrOfCapitalizedCharacters, java.lang.Long NrOfLowercaseCharacters, java.lang.Long NrOfDigits, java.lang.Long NrOfSpecialCharacters)
	{
		super(context);
		this.MinLength = MinLength;
		this.MaxLength = MaxLength;
		this.NrOfCapitalizedCharacters = NrOfCapitalizedCharacters;
		this.NrOfLowercaseCharacters = NrOfLowercaseCharacters;
		this.NrOfDigits = NrOfDigits;
		this.NrOfSpecialCharacters = NrOfSpecialCharacters;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		return StringUtils.randomStrongPassword(
			safeLongToInt(MinLength),
			safeLongToInt(MaxLength),
			safeLongToInt(NrOfCapitalizedCharacters),
			safeLongToInt(NrOfLowercaseCharacters),
			safeLongToInt(NrOfDigits),
			safeLongToInt(NrOfSpecialCharacters)
		);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "RandomStrongPasswordWithLowercase";
	}

	// BEGIN EXTRA CODE
	public static int safeLongToInt(Long l) {
		if (l == null) return 0;
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
		}
		return l.intValue();
	}
	// END EXTRA CODE
}
