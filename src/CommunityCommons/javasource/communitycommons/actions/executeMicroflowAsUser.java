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
 * Executes the given microflow as if the $currentuser is the provided user (delegation). Use sudoContext to determine if 'apply entity access' should be used 
 * 
 * - microflowName: the fully qualified microflow name, 'CommunityCommons.CreateUserIfNotExists'
 * - username: The user that should be used to execute the microflow
 * - sudoContext: whether entity access should be applied.
 */
public class executeMicroflowAsUser extends UserAction<java.lang.String>
{
	private final java.lang.String microflow;
	private final java.lang.String username;
	private final java.lang.Boolean sudoContext;

	public executeMicroflowAsUser(
		IContext context,
		java.lang.String _microflow,
		java.lang.String _username,
		java.lang.Boolean _sudoContext
	)
	{
		super(context);
		this.microflow = _microflow;
		this.username = _username;
		this.sudoContext = _sudoContext;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		Object res = Misc.executeMicroflowAsUser(getContext(), microflow, username, sudoContext);
		return res == null ? null : res.toString();
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "executeMicroflowAsUser";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
