// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import communitycommons.Misc;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * This action always throws an exception (of type communityutils.UserThrownError), which is, in combination with custom error handling, quite useful to end a microflow prematurely or to bail out to the calling action/ microflow. 
 * 
 * The message of the last thrown error can be inspected by using the variable $lasterrormessage
 * 
 * Example usuage: In general, if an Event (before commit especially) returns false, it should call this action and then return true instead. If an Before commit returns false, the object will not be committed, but there is no easy way for the calling Microflow/ action to detect this! An exception on the other hand, will be noticed.
 */
public class ThrowException extends UserAction<java.lang.Boolean>
{
	private final java.lang.String message;

	public ThrowException(
		IContext context,
		java.lang.String _message
	)
	{
		super(context);
		this.message = _message;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		Misc.throwException(message);
		return null;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "ThrowException";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
