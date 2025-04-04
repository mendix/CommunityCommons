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
import communitycommons.StringUtils;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.UserAction;

/**
 * Given an object and a template, substitutes all fields in the template. Supports attributes, references, referencesets and constants. 
 * 
 * The general field syntax is '{fieldname}'. 
 * 
 * Fieldname can be a member of the example object, an attribute which need to be retrieved over an reference(set) or a project constant. All paths are relative from the provided substitute obect. An example template:
 * ------------------
 * Dear {EmailOrName},
 * 
 * {System.changedBy/FullName} has invited you to join the project {Module.MemberShip_Project/Name}. 
 * Sign up is free and can be done here:
 * {@Module.PublicURL}link/Signup
 * -------------------------
 * 
 * useHTMLEncoding identifies whether HTMLEncode is applied to the values before substituting.
 * 
 * datetimeformat identifies a format string which is applied to date/time based attributes. Can be left empty. Defaults to "EEE dd MMM yyyy, HH:mm"
 */
public class SubstituteTemplate extends UserAction<java.lang.String>
{
	private final java.lang.String template;
	private final IMendixObject substitute;
	private final java.lang.Boolean useHTMLEncoding;

	public SubstituteTemplate(
		IContext context,
		java.lang.String _template,
		IMendixObject _substitute,
		java.lang.Boolean _useHTMLEncoding
	)
	{
		super(context);
		this.template = _template;
		this.substitute = _substitute;
		this.useHTMLEncoding = _useHTMLEncoding;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		return StringUtils.substituteTemplate(this.getContext(), template, substitute, useHTMLEncoding, null);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "SubstituteTemplate";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
