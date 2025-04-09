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
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * Pads a string on the left to a certain length. 
 * value : the original value
 * amount: the desired length of the resulting string.
 * fillCharacter: the character to pad with. (or space if empty)
 * 
 * For example
 * StringLeftPad("hello", 8, "-")  returns "---hello"
 * StringLeftPad("hello", 2, "-")  returns "hello"
 */
public class StringLeftPad extends UserAction<java.lang.String>
{
	private final java.lang.String value;
	private final java.lang.Long amount;
	private final java.lang.String fillCharacter;

	public StringLeftPad(
		IContext context,
		java.lang.String _value,
		java.lang.Long _amount,
		java.lang.String _fillCharacter
	)
	{
		super(context);
		this.value = _value;
		this.amount = _amount;
		this.fillCharacter = _fillCharacter;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		return communitycommons.StringUtils.leftPad(value, amount, fillCharacter);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "StringLeftPad";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
