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
import communitycommons.Misc;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * This action allows an microflow to be executed independently from this microflow. 
 * This function is identical to "RunMicroflowAsyncInQueue", except that it takes one argument which will be passed to the microflow being called. 
 * 
 * This might be useful to model for example your own batching system, or to run a microflow in its own (system) transaction. The microflow is delayed for at least 200ms and then run with low priority in a system context. Since the microflow run in its own transaction, it is not affected with rollbacks (due to exceptions) or commits in this microflow. 
 * 
 * Invocations to this method are guaranteed to be run in FIFO order, only one microflow is run at a time. 
 * 
 * Note that since the microflow is run as system transaction, $currentUser is not available and no security restrictions are applied. 
 * 
 * - The microflowname specifies the fully qualified name of the microflow (case sensitive) e.g.: 'MyFirstModule.MyFirstMicroflow'. 
 * - The context object specifies an argument that should be passed to the microflow if applicable. Currently only zero or one argument are supported. Note that editing this object in both microflows might lead to unexpected behavior.
 * 
 * Returns true if scheduled successfully.
 */
public class executeMicroflowInBackground extends UserAction<java.lang.Boolean>
{
	private final java.lang.String microflow;
	private final IMendixObject contextObject;

	public executeMicroflowInBackground(
		IContext context,
		java.lang.String _microflow,
		IMendixObject _contextObject
	)
	{
		super(context);
		this.microflow = _microflow;
		this.contextObject = _contextObject;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		return  Misc.runMicroflowInBackground(getContext(), microflow, contextObject);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "executeMicroflowInBackground";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
