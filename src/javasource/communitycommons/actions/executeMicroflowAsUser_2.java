// This file was generated by Mendix Modeler.
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
import com.mendix.webui.CustomJavaAction;

/**
 * Identical to executeMicroflowAsUser, but takes 2 arguments
 */
public class executeMicroflowAsUser_2 extends CustomJavaAction<String>
{
	private String microflowName;
	private String username;
	private Boolean sudoContext;
	private String arg1name;
	private IMendixObject arg1value;
	private String arg2name;
	private IMendixObject arg2value;

	public executeMicroflowAsUser_2(IContext context, String microflowName, String username, Boolean sudoContext, String arg1name, IMendixObject arg1value, String arg2name, IMendixObject arg2value)
	{
		super(context);
		this.microflowName = microflowName;
		this.username = username;
		this.sudoContext = sudoContext;
		this.arg1name = arg1name;
		this.arg1value = arg1value;
		this.arg2name = arg2name;
		this.arg2value = arg2value;
	}

	@Override
	public String executeAction() throws Exception
	{
		// BEGIN USER CODE
		Object res = Misc.executeMicroflowAsUser(getContext(), microflowName, username, sudoContext, arg1name, arg1value, arg2name, arg2value);
		return res == null ? null : res.toString();
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "executeMicroflowAsUser_2";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
