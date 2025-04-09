// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * This function commits an object in a seperate context and transaction, making sure it gets persisted in the database (regarding which exception happens after invocation).
 */
public class commitInSeparateDatabaseTransaction extends UserAction<java.lang.Boolean>
{
	private final IMendixObject mxObject;

	public commitInSeparateDatabaseTransaction(
		IContext context,
		IMendixObject _mxObject
	)
	{
		super(context);
		this.mxObject = _mxObject;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		ISession session = getContext().getSession();
		IContext newContext = session.createContext();
		Core.commit(newContext, mxObject);
		newContext.endTransaction();
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "commitInSeparateDatabaseTransaction";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
