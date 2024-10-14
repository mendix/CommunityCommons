// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import com.mendix.systemwideinterfaces.core.IMendixObject;
import communitycommons.ORM;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;

/**
 * Commits an object, but without events. 
 * 
 * N.B. This function is not very useful when called from the model, but it is useful when called from custom Java code.
 */
public class commitWithoutEvents extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject subject;

	public commitWithoutEvents(IContext context, IMendixObject subject)
	{
		super(context);
		this.subject = subject;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		return ORM.commitWithoutEvents(this.getContext(), subject);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "commitWithoutEvents";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
