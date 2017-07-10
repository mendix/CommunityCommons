// This file was generated by Mendix Modeler.
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

/**
 * Causes this request to sleep for a while. Useful to prevent brute force attacks or to simulate latency delays. 
 *  
 * Delaytime : time in ms
 */
public class Delay extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.Long delaytime;

	public Delay(IContext context, java.lang.Long delaytime)
	{
		super(context);
		this.delaytime = delaytime;
	}

	@Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		Misc.delay(delaytime);
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "Delay";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
